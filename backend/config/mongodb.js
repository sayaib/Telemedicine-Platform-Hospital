import mongoose from "mongoose";

const connectDB = async () => {
  try {
    await mongoose.connect(process.env.MONGODB_URI, {
      tls: true,
      serverApi: { version: "1", strict: true, deprecationErrors: true },
    });

    console.log("✅ MongoDB connected");

    app.listen(3000, () => {
      console.log("🚀 Server is running on port 3000");
    });
  } catch (err) {
    console.error("DB connection failed:", err);
  }
};

export default connectDB;
