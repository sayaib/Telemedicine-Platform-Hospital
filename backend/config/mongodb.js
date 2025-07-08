import mongoose from "mongoose";

const connectDB = async () => {
  try {
    await mongoose.connect(process.env.MONGODB_URI, {
      tls: true,
      tlsAllowInvalidCertificates: false, // change to true ONLY if you use self-signed certs
      tlsAllowInvalidHostnames: false,
      serverApi: { version: "1", strict: true, deprecationErrors: true },
    });

    console.log("Successfully connected to MongoDB using Mongoose");
  } catch (err) {
    console.error("Mongoose connection error:", err);
  } finally {
    await mongoose.disconnect(); // optional: disconnect after testing
  }
};

export default connectDB;
