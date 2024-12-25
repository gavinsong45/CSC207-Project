# GESTUREBRIDGE —— HUMAN CONNECTION FOR ALL 👋🌉

## PROJECT OVERVIEW
**GestureBridge** is a groundbreaking **sign language translation application** that leverages machine learning to create seamless communication between individuals who use sign language and those who don't. This innovative platform captures ASL sign language gestures through a camera, interprets them, converts them into written text, and vocalizes that text—offering a **real-time, accessible communication experience**.

At its core, GestureBridge is more a bridge to fostering **greater inclusivity and understanding** between communities. By breaking down communication barriers, we hope to empower individuals who are deaf or hard of hearing, as well as their families, friends, and colleagues. We believe that **human connection** is the foundation of any thriving society, and our mission is to help people connect through language, regardless of the medium they use. 🤝💬

The platform will initially support ASL fingerspelling, with plans to extend vocabulary and functionality over time. GestureBridge will continually evolve to offer better, more accurate sign language recognition and translation, creating a more inclusive environment for all.

## CONTRIBUTORS
- Wania Sikandar Gondal ([@waniagondal](https://github.com/waniagondal))
- Yibin Wang ([@YIBIN35](https://github.com/YIBIN35))
- Fang (Trent) Sheng ([F4NG66](https://github.com/F4NG66))
- Gavin Jiawei Song ([@gavinsong45](https://github.com/gavinsong45))
- Shuxiao Song ([@SSX-song](https://github.com/SSX-song))

## TABLE OF CONTENTS
- [**GESTUREBRIDGE MANIFESTO**](#gesturebridge-manifesto)
- [**FEATURES**](#features)
- [**INSTALLATION GUIDE**](#installation-guide)
- [**USAGE GUIDE**](#usage-guide)
- [**LICENSE**](#license)
- [**FEEDBACK AND CONTRIBUTIONS**](#feedback-and-contributions)

## GESTUREBRIDGE MANIFESTO
At **GestureBridge**, we don’t just create software—we create **connections**. Our mission is to build a world where **language is no barrier** to understanding, communication, and human connection. We believe that **language**—whether spoken, written, or gestured—should empower people to share ideas, thoughts, and emotions without limitations. 

By using **GestureBridge**, you're not just accessing a tool—you're contributing to **a future where inclusivity, empathy, and understanding are the foundation** of all interactions. Together, we aim to create a more **accessible world** where **everyone is heard**, and **everyone belongs**, no matter how they communicate. 🌍💬

## FEATURES
- **Real-Time Sign Language Recognition** 🤖: GestureBridge can instantly recognize sign language via your webcam feed, allowing seamless interaction. Currently, the application supports fingerspelling, with plans to incorporate more gestures in future updates.
  
```
    public void startRecognition() throws IOException, InterruptedException {
        predictor.startRecognition(this::predictionToPresenter);
    }
    private void predictionToPresenter(String prediction) {
        SignLanguageRecognitionOutputData outputData = new SignLanguageRecognitionOutputData(prediction);
        signLanguagePresenter.updateView(outputData);
    }
```

- **Multilingual Language Translation** 🌐: Recognized signs are translated into multiple languages, broadening communication between individuals across linguistic divides.
  
```
    String translatedText = languageDataAccessObject.translate(language, text);
    SignLanguageTranslationOutputData signLanguageTranslationOutputData =
        new SignLanguageTranslationOutputData(translatedText);
    signLanguageTranslationPresenter.prepareSuccessView(signLanguageTranslationOutputData);
```

- **Text-to-Speech Conversion** 🔊: GestureBridge vocalizes the recognized text in real-time, allowing for spoken communication.

```
    TextToSpeechOutputData outputData = textToSpeechService.convertTextToSpeech(inputData);
    outputBoundary.prepareOutput(outputData);
```

- **Voice Customization 🎤**: Users can personalize the speech output by adjusting voice type, pitch, speed, and volume, making the interaction feel more natural, and respecting the identities of the users.

```
    voiceSettingsDataAccessObject.changeSettings(audioSettings);

    final CustomizeVoiceOutputData customizeVoiceOutputData = new CustomizeVoiceOutputData(audioSettings, false);
    voiceSettingsPresenter.prepareSuccessView(customizeVoiceOutputData);
```

- **Speech-to-Text Transcription** 📝: Voice input from the other individual is transcribed into text, displayed for the deaf or hard of hearing person.

```
    public void processSpeech(SpeechToTextInputData inputData) throws Exception {
        String onSpeechRecognition = speechRecognizer.recognize(inputData.getAudioData());
        SpeechToTextOutputData outputData = new SpeechToTextOutputData(onSpeechRecognition);
        speechToTextPresenter.deliverTranscription(outputData);
    }
```

## INSTALLATION GUIDE
- ### Requirements ⚙️
    - **Python** (version 3.7 or higher)
    - **MediaPipe** (version 0.10.18)
    - **NumPy** (version 1.26.4)
    - **OpenCV-Python** (version 4.10.0.84)
    - **Pytorch** (version 2.5.1)
    - **TorchVision** (version 0.20.1)
    - **Google Cloud Libraries** (version 26.49.0) for Cloud Translation, Speech-to-Text, and Text-to-Speech APIs
    - **CSC207 CheckStyle** (for code formatting)

- ### Installing Python and Machine Learning Dependencies 🐍
    - [Download Python](https://www.python.org/downloads/) (version 3.7 or higher) and install it.
    - To install required Python libraries, run (in Python):

        ```bash
        pip install -r model_requirements.txt
        ```
    
- ### Installing Google Cloud APIs ☁️
    - Install the **Google Cloud APIs** by following the setup guides:
        - [Cloud Translation API](https://cloud.google.com/translate/docs/setup)
        - [Cloud Text-to-Speech API](https://cloud.google.com/text-to-speech/docs/before-you-begin)
        - [Cloud Speech-to-Text API](https://cloud.google.com/speech-to-text/docs/before-you-begin)

- ### Installing Checkstyle ✅
    - We recommend using IDEs for this project (e.g., IntelliJ IDEA, Eclipse). To install the CSC207 checkstyle, make sure the file
    mystyle.xml is included in your repository, then paste the following code to checkstyle.xml file
    ```
    <ConfigurationLocation id="a12e12d0-c511-43fb-8087-db6b548f5394" type="PROJECT_RELATIVE" scope="All" description="207 Checks">$PROJECT_DIR$/mystyle.xml</ConfigurationLocation>
    ```
    - Set the checkstyle as default to check your code any time.

- ### FAQ on Software Installation and Troubleshooting 🆘

    - **Q: Is this software limited to specific operating systems or hardware?**
    - **A:** This software was developed and tested on macOS (10.15 or later). It should work on other operating systems (Windows and Linux), but you may need to take some additional steps to ensure full compatibility.
        - Operating Systems:
            - macOS (10.15 or later)
            - Windows (Windows 10 or later) - For Windows, ensure you have a compatible Java Development Kit (JDK) installed (Java 8 or later). You might need to set up the environment variables (like JAVA_HOME) correctly.
            - Linux (Ubuntu 20.04 or later or equivalent) - For Linux, you may need to install dependencies via package managers like apt (for Ubuntu) and ensure your environment matches the requirements.
        - Hardware Requirements:
            - Minimum 4 GB RAM (8 GB recommended)
            - 1 GB of free disk space (more may be needed for dependencies)
            - Multi-core processor (2 GHz or faster recommended)
       
    - **Q: I added the dependencies in my pom.xml file, but the program still doesn't run. Why?**
    - **A:** This issue usually happens if your IDE has not properly loaded or synchronized the dependencies after they were added to the pom.xml file.
      - **Solution:**
          - Click on the pom.xml file in your IDE.
          - Go to the "Maven" menu option and click Reload Project (in IntelliJ IDEA, this is usually in the right-hand sidebar).
          - If this doesn't solve the issue, try re-importing the dependencies:
              - Right-click on the pom.xml file and select Reimport.
              - Ensure that the dependencies are in the correct versions by checking the Maven tool window.
    
    - **Q: I tried running the program, but I'm getting API errors. What should I do?**
    - **A:** API errors are often caused by missing or incorrect API keys.
      - **Solution:**
          - Ensure you have generated and correctly inserted a valid API key for the service you are trying to use (e.g., Google Cloud APIs).
          - For Google Cloud APIs:
              - Go to the [Google Cloud Console](https://cloud.google.com/apis/docs/getting-started).
              - Create a new API key for your project.
              - Insert the generated API key into your project configuration.
              - Double-check that your API key has the correct permissions and is authorized to access the necessary resources.
    
    - **Q: My Checkstyle configuration isn't working. What could be the problem?**
    - **A:** This issue is typically caused by a missing or misconfigured Checkstyle configuration file.
      - **Solution:**
          - Ensure that the mystyle.xml Checkstyle configuration file is included in your repository.
          - Set Checkstyle as the default code checker in your IDE:
              - In IntelliJ IDEA, go to Preferences > Code Style > Checkstyle.
              - Set your custom mystyle.xml as the default Checkstyle configuration.
              - Ensure that Checkstyle is enabled for code inspections.
    
    - **Q: What IDEs are recommended for this project?**
    - **A:** It is highly recommended to use an IDE such as IntelliJ IDEA or Eclipse for easier development and debugging. These IDEs support dependency management, running programs, and configuring additional tools like Checkstyle.
    
    - **Q: What should I do if the program still doesn't run after resolving these issues?**
    - **A:** Try the following general troubleshooting steps:
        - **Verify Java Version:** Make sure the Java version installed on your system is compatible with the project requirements (22.0.2) Check it using the command java -version in the terminal.
        - **Rebuild Project:** If the program doesn't run even after resolving the dependencies, try rebuilding the project by selecting Build > Rebuild Project in your IDE.
        - **Check Network Connectivity:** Ensure your network connection is stable, especially if you're fetching dependencies from an external repository (like Maven Central).

## USAGE GUIDE
- **Webcam Access**: After downloading, ensure your webcam is accessible for sign language recognition.
-  **Gesture Recognition**: Perform a gesture to express the content, which will appear in the “Sign Language Recognition” text box.
-  **Translation**: Choose a language from the dropdown and click “Translate” to convert the recognized text into another language.

https://github.com/user-attachments/assets/1fb94de8-56ec-4cf3-808c-aa642d2d0cf1

- **Text-to-Speech**: After translating the text, click “Text to Speech” to hear the translation.

https://github.com/user-attachments/assets/98782227-67ba-4af5-92a1-0d3fb0cc90cf
 
- **Voice Customization**: Modify the voice settings (e.g., type, pitch, speed) by clicking the “Settings” button.

https://github.com/user-attachments/assets/1e16e11c-c808-40c7-af77-c3f6855e25c7

- **Speech-to-Text**: To transcribe spoken language into text, click “Begin Transcription” and speak into the microphone, then click "End Transcription" once you have finished. Your transcription will appear in the transcription area.

https://github.com/user-attachments/assets/2aa36b9e-4560-4695-8f43-04b9d3be390e

## LICENSE
This project is licensed under the **Creative Commons** license. 

## FEEDBACK AND CONTRIBUTIONS
We believe in the power of **collaboration** and the **importance of human connection**. Your feedback and contributions are vital to us. By participating, you're helping us create a more inclusive world where communication knows no barriers. 🌏

- ### Feedback 🗣️
    - **How to Provide Feedback:**
        - Send an email to [wania.gondal@mail.utoronto.ca](mailto:wania.gondal@mail.utoronto.ca) with "GestureBridge" in the subject.
        - Include your feedback, along with any relevant screenshots or code snippets.
        - You will receive an acknowledgment of your feedback within 48 hours.
        - Please check the issues tracker for further updates. If you provided your GitHub username when giving feedback, we will notify you when posting new changes.
    - **Guidelines for Providing Valid Feedback: GestureBridge's FABRIC Method**
        - **F [Feature]:** If you're suggesting a new feature, explain why it would benefit the project or users. Discuss the impact it might have and, if possible, how it can be implemented while aligning with the project's overall goals.
        - **A [Actionable]:** Your feedback should suggest possible improvements, solutions, or changes that are clear and achievable. Simply pointing out problems without suggesting a solution can delay progress.
        - **B [Bug Reporting]:** If you are reporting a bug, provide detailed information on how to reproduce the issue, the environment you encountered it in, and any error messages or logs. This allows the maintainers to pinpoint the problem faster.
        - **R [Relevance]:** Ensure your feedback is relevant to the current project and within its scope. Feedback unrelated to the project or its goals may not be prioritized.
        - **I [Improvement]:** If you have suggestions for improving the user interface (UI), user experience (UX), or codebase, ensure they are clear, practical, and beneficial to the overall design or performance.
        - **C [Clear]:** Provide detailed feedback, clearly explaining the issue or suggestion. Avoid vague statements like “it doesn't work” or “it’s bad.” Instead, focus on specific problems and how they affect the user or functionality.
  - **What to Expect When Submitting Feedback:**
      - After you submit your feedback, it will be reviewed by the maintainers. Expect a response within 1-2 weeks, depending on the volume of submissions.
      - If your feedback suggests a bug or issue, it may be prioritized and fixed in an upcoming release.
      - If you propose a feature, it may be considered for future development based on its relevance and importance.
- ### Contributions 🤝
    - **How to Contribute**
        - **Fork the Repository**: Click the "Fork" button on GitHub.
        - **Clone Your Fork**: Clone the repository to your local machine using Git or GitHub Desktop.
        - **Create a Branch**:  
              ```bash
              git checkout -b feature/your-feature-name
              ```
        - **Make Your Changes**: Code, improve documentation, or fix bugs.
          **Commit Your Changes**:
              ```bash
              git commit -m "Your description of changes"
              ```
        - **Push to Your Fork**:
              ```bash
              git push origin feature/your-feature-name
              ```
        - **Submit a Pull Request**: Open a PR on GitHub to propose your changes.
    - #### Guidelines for a Good Pull Request
        - **Ensure your branch is up to date with `main'.**
            - Before making any changes, ensure your branch is up to date with the latest main branch to avoid conflicts. Run:
                ```
                git checkout main
                git pull origin main
                git checkout <your-branch>
                git merge main
                ```
        - **Provide a clear and detailed description of your changes.**
            - When creating a pull request, write a detailed description of the changes made. Describe what the problem is, how your changes fix it, and any context that might be helpful for reviewers.
            - Include information about any testing or verification steps you took to ensure the changes work as expected.
        - **Adhere to CSC207 CheckStyle and Clean Architecture / SOLID Principles:**
            - **CheckStyle:** Ensure your code adheres to the CSC207 CheckStyle guidelines. If unsure, run the CheckStyle in your IDE or use the command line to verify your code before submitting. Follow the correct indentation, naming conventions, and other style rules
            - **Clean Architecture:** Structure your code so that it follows Clean Architecture principles, ensuring clear separation of concerns and maintaining scalability and maintainability. A Clean Architecture Guide can be found [here](https://github.com/GunterMueller/Books-3/blob/master/Clean%20Architecture%20A%20Craftsman%20Guide%20to%20Software%20Structure%20and%20Design.pdf)
            - **SOLID Principles:** Ensure your code follows SOLID principles, which are:
                - **S:** Single Responsibility Principle (SRP) – Each class should have only one reason to change.
                - **O:** Open/Closed Principle (OCP) – The software should be open for extension but closed for modification.
                - **L:** Liskov Substitution Principle (LSP) – Subtypes must be substitutable for their base types.
                - **I:** Interface Segregation Principle (ISP) – Clients should not be forced to depend on interfaces they do not use.
                - **D:** Dependency Inversion Principle (DIP) – High-level modules should not depend on low-level modules. Both should depend on abstractions.
    - #### Review and Merge Protocols
      - **Weekly Review of Contributions:** All contributions and pull requests will be reviewed weekly by the maintainers. Be patient, as review times may vary depending on the volume of submissions.
      - **Purpose of Contributions:** Your pull request should address an existing issue or add a meaningful feature. Ensure that it provides value to the project.
      - **Checkstyle Compliance:** Your contribution should pass all CheckStyle scans before it is pushed. If there are any CheckStyle errors, they must be fixed before the pull request can be reviewed.
      - **Approval and Merging:** Once your pull request is reviewed and approved by the maintainers, it will be merged into the main branch. After merging, you will be credited as a contributor in the project’s history.
