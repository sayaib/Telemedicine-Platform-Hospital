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

// ES module dirname handling
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

// Initialize app
const app = express();
const port = process.env.PORT || 4000;

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

// Serve static files for user site
const userDist = path.join(__dirname, "dist");
app.use("/", express.static(userDist));

// Serve static files for admin panel
const adminDist = path.join(__dirname, "admin");
app.use("/admin", express.static(adminDist));

// Fallback routing for user app
app.get("/", (req, res) => {
  res.sendFile(path.join(userDist, "index.html"));
});

// Fallback routing for admin app
app.get("/admin/*", (req, res) => {
  res.sendFile(path.join(adminDist, "index.html"));
});

// Start server
app.listen(port, () => console.log(`Server started on PORT: ${port}`));
