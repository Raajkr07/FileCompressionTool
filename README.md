# **File Compression Tool** 🔥

A **Java-based file compression tool** that implements three compression algorithms:  
✔ **Huffman Encoding** (Lossless Compression)  
✔ **LZW (Lempel–Ziv–Welch) Compression**  
✔ **Run-Length Encoding (RLE)**  
✔ **Graphical User Interface (GUI)**  
✔ **Multi-threading for large files**  
✔ **File selection, compression, and decompression**

This tool comes with a **GUI built using Swing**, allowing users to compress and decompress files easily.

---

## **📌 Features**
✅ **Huffman Compression** – Best for text-heavy files 🐝  
✅ **LZW Compression** – Efficient dictionary-based encoding 🐂  
✅ **RLE Compression** – Simple and fast for repetitive data 🔀  
✅ **Graphical User Interface (GUI)** – Easy-to-use file selection & operations 🎨  
✅ **Multi-threading Support** – Ensures smooth performance ⚡

---

## **📂 Project Structure**
```
📛 FileCompressionTool  
 ┣ 📂 src  
 ┃ ┣ 📂 compression  
 ┃ ┃ ┣ 📄 HuffmanCompression.java  
 ┃ ┃ ┣ 📄 LZWCompression.java  
 ┃ ┃ ┣ 📄 RLECompression.java  
 ┃ ┃ ┣ 📄 HuffmanNode.java  
 ┃ ┣ 📂 ui  
 ┃ ┃ ┣ 📄 CompressionGUI.java  
 ┃ ┣ 📄 Main.java  
 ┣ 📄 README.md  
 ┣ 📄 .gitignore  
 ┣ 📄 LICENSE  
```

---

## **🚀 How to Run**
### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/Rajkr07/FileCompressionTool.git
cd FileCompressionTool
```

### **2️⃣ Compile and Run**
#### **🔹 Run from Terminal (CLI)**
```bash
javac -d out src/compression/*.java src/ui/*.java
java -cp out ui.CompressionGUI
```

#### **🔹 Run from an IDE (IntelliJ / Eclipse)**
- Open the project in your IDE
- Set **`ui.CompressionGUI`** as the **Main Class**
- Click **Run** ▶

---

## **🛠 Technologies Used**
🔹 **Java** (JDK 23)  
🔹 **Swing** (GUI Framework)  
🔹 **Collections Framework** (HashMap, PriorityQueue, etc.)  
🔹 **File I/O** (Reading & Writing Files)

---

## **🎯 Future Improvements**
✅ Add support for **image & binary file compression** 🌟  
✅ Implement **zip format support** 📦  
✅ Improve UI with **JavaFX or modern frameworks** ✨

---

## **📝 License**
This project is **open-source** under the **MIT License**.

---

🔗 **Contribute & Star** ⭐ this project on GitHub if you find it useful! 😃