import express from "express";
import cors from "cors";
import "dotenv/config";
import path from "path";
import { fileURLToPath } from "url";
import connectDB from "./config/mongodb.js";
import connectCloudinary from "./config/cloudinary.js";
import userRouter from "./routes/userRoute.js";
import doctorRouter from "./routes/doctorRoute.js";
import adminRouter from "./routes/adminRoute.js";

// Handle __dirname in ES modules
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// Process-level error handlers
process.on("uncaughtException", (err) => {
  console.error("Uncaught Exception:", err);
});

process.on("unhandledRejection", (reason, promise) => {
  console.error("Unhandled Rejection:", reason);
});

// Initialize app
const app = express();
const port = process.env.PORT || 4000;

// Connect database and cloud storage
connectDB();
connectCloudinary();

// Middlewares
app.use(express.json());
app.use(
  cors({
    origin: "*",
    credentials: true,
  })
);

// API routes
app.use("/api/user", userRouter);
app.use("/api/admin", adminRouter);
app.use("/api/doctor", doctorRouter);

// Static serving
const userDist = path.join(__dirname, "dist");
const adminDist = path.join(__dirname, "admin");

app.use("/", express.static(userDist));
app.use("/admin", express.static(adminDist));

// Fallback routing for SPA (React, Vue, etc.)
app.get("/", (req, res) => {
  res.sendFile(path.join(userDist, "index.html"));
});

app.get("/admin/*", (req, res) => {
  res.sendFile(path.join(adminDist, "index.html"));
});

// Global error handler middleware
app.use((err, req, res, next) => {
  console.error("Global Error:", err.stack || err.message);
  res.status(500).json({ success: false, message: "Internal Server Error" });
});

// Start server
app.listen(port, () => {
  console.log(`✅ Server started on PORT: ${port}`);
});
