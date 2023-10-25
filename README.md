# OCR Snipper

## Description
OCR Snipper is a simple utility that allows you to capture screen snips with OCR (Optical Character Recognition) capabilities. It's designed to be quick and easy to use. You can toggle the snipping mode with the "Scroll Lock" key, and select an area on the screen using your mouse. To end the program, you can press "ESC."

## Features
- Toggle snipping mode with the "Scroll Lock" key.
- Capture a snip of a selected area on the screen using your mouse.
- Utilize OCR to extract text from the captured snip.
- The application can be exited by pressing "ESC," which terminates the program.

## Limitations
- The initial version operates only on the monitor where it is executed.

Feel free to enhance OCR Snipper and contribute to its development!
___

# Setting up Maven and Java 21 on Windows, Linux, and Mac

This guide will walk you through the steps to download, install, and configure Maven and Java 21 on Windows, Linux, and Mac systems.

## Download and Install Maven

1. **Download Maven**:
    - Visit the Apache Maven download page: [Maven Download](https://maven.apache.org/download.cgi)
    - Download the latest binary zip file (e.g., `apache-maven-3.x.x-bin.zip`).

2. **Install Maven**:
    - Extract the downloaded zip file to a directory of your choice.
    - For simplicity, let's assume you extracted it to `/opt/apache/` on Linux and Mac or `C:\Program Files\Apache\` on Windows.

3. **Set the M2_HOME Environment Variable**:
    - For Windows:
        - Right-click on "This PC" or "My Computer" and select "Properties."
        - Click on "Advanced system settings."
        - Click "Environment Variables" in the "Advanced" tab.
        - Under "System Variables," click "New" and add a variable named `M2_HOME` with the path to your Maven installation (e.g., `C:\Program Files\Apache\apache-maven-3.x.x`).
    - For Linux and Mac:
        - Open a terminal and edit your shell profile file (e.g., `~/.bashrc`, `~/.zshrc`, or `~/.profile`).
        - Add the following line: `export M2_HOME=/opt/apache/apache-maven-3.x.x`.
        - Reload your shell profile or restart the terminal.

4. **Add Maven to the System Path**:
    - For Windows:
        - Find the "Path" variable under "System Variables" and click "Edit."
        - Add `%M2_HOME%\bin` to the list of paths. Ensure there are no spaces between entries.
    - For Linux and Mac:
        - The `bin` directory of Maven is automatically included in your shell's `PATH` when you set `M2_HOME`.

5. **Verify Maven Installation**:
    - Open a command prompt or terminal and run `mvn -version` to confirm that Maven is correctly installed.

## Download and Install Java 21

1. **Download Java 21**:
    - Visit the Oracle JDK download page: [Oracle JDK Downloads](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://jdk.java.net/).
    - Download the latest version of Java 21 (or later) for your platform.

2. **Install Java**:
    - Run the downloaded installer and follow the installation instructions.

3. **Set the JAVA_HOME Environment Variable**:
    - For Windows:
        - Right-click on "This PC" or "My Computer" and select "Properties."
        - Click on "Advanced system settings."
        - Click "Environment Variables" in the "Advanced" tab.
        - Under "System Variables," click "New" and add a variable named `JAVA_HOME` with the path to your Java 21 installation directory (e.g., `C:\Program Files\Java\jdk-21.x.x`).
    - For Linux and Mac:
        - Open a terminal and edit your shell profile file.
        - Add the following line: `export JAVA_HOME=/usr/lib/jvm/jdk-21.x.x` (Linux) or `export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.x.x/Contents/Home` (Mac).
        - Reload your shell profile or restart the terminal.

4. **Add Java to the System Path**:
    - For Windows:
        - Find the "Path" variable under "System Variables" and click "Edit."
        - Add `%JAVA_HOME%\bin` to the list of paths. Ensure there are no spaces between entries.
    - For Linux and Mac:
        - The `bin` directory of Java is automatically included in your shell's `PATH` when you set `JAVA_HOME`.

5. **Verify Java Installation**:
    - Open a command prompt or terminal and run `java -version` to confirm that Java 21 is correctly installed.

## Running Your Application

Now that Maven and Java 21 are installed and configured:

1. Navigate to your project directory where the `pom.xml` file is located.

2. Open a command prompt or terminal in that directory.

3. Use Maven to build and run your application. For example:
    - Compile: `mvn compile`
    - Package: `mvn package`
    - Run: `java -jar target/ocr-snipper-0.0.1-SNAPSHOT-jar-with-dependencies.jar`

You are now ready to develop and run Java applications using Maven and Java 21 on your Windows, Linux, or Mac system.
