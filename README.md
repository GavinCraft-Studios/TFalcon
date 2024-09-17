# TFalcon
#### Text Falcon is a Java based text editor for all operating systems.



## Build .jar from IntelliJ
#### I would like to leave instructions for myself and others on how to build a working .jar file from the editor this is made in (IntelliJ).

- Open the Project Structure dialog:
  - Go to File > Project Structure (or press Ctrl+Alt+Shift+S on Windows/Linux or Cmd+Alt+Shift+S on macOS).
- Add a JAR artifact:
  - In the Project Structure dialog, navigate to Artifacts under Project Settings.
  - Click the + button to add a new artifact.
  - Select JAR and then choose From modules with dependencies.
- Configure the JAR artifact:
  - In the Create JAR from Modules window, select the main class for your application (e.g., HelloWorld).
  - Click OK to close the window.
- Verify the output directory:
  - In the Project Structure dialog, check the Output directory path to ensure your JAR file will be generated in the correct location.
- Build the JAR file:
  - Go to Build > Build Artifacts (or press Ctrl+F9 on Windows/Linux or Cmd+F9 on macOS).
  - Select the JAR artifact you created in step 2.
  - Click Build to generate the JAR file.
- Find the generated JAR file:
  - The JAR file will be located in the output directory you specified in step 4.