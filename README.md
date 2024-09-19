# Idea Generator

This project is an innovative Idea Generator that leverages a curated list of APIs to spark creativity and generate unique project ideas. It's designed to inspire developers, entrepreneurs, and creative thinkers by combining various APIs in unexpected ways.

## Features

- **API-Based Idea Generation**: Utilizes a diverse list of APIs to generate 10 unique project ideas.
- **Custom Prompts**: Users can edit the prompt sent to the Language Model (LLM) for more tailored results.
- **Conversation History**: All interactions and generated ideas are saved in a Microsoft SQL database for future reference.
- **Chat with LLM**: A separate feature allows users to have open-ended conversations with the LLM.
- **PDF Download**: Users can download their generated ideas and conversations as a PDF file.

## Technology Stack

- **Backend**: Java with Spring Boot
- **Frontend**: HTML, CSS, JavaScript
- **Database**: Microsoft SQL Server
- **LLM Integration**: Groq API (using the llama3-8b-8192 model)

## Key Components

1. **IdeaService**: Core service for generating ideas and managing conversations.
2. **IOManager**: Handles reading the API list and selecting random APIs for idea generation.
3. **LLMManager**: Manages interactions with the Language Model via the Groq API.
4. **Conversation & Message Models**: Represent the structure for storing conversation history.
5. **HTML Frontend**: Provides a user-friendly interface for interacting with the Idea Generator.

## Getting Started

1. Clone the repository:
   ```
   git clone https://github.com/mariussarca/idea-generator.git
   ```

2. Set up the Microsoft SQL Server database and update the connection details in `application.properties`.

3. Ensure you have Java and Maven installed on your system.

4. Build the project:
   ```
   mvn clean install
   ```

5. Run the application:
   ```
   java -jar target/idea-generator.jar
   ```

6. Open a web browser and navigate to `http://localhost:8080` to access the Idea Generator.

## Usage

1. **Generate Ideas**: Click the "Generate Idea" button to receive 10 unique project ideas based on random API combinations.
2. **Custom Prompts**: Edit the prompt in the text area before generating ideas for more specific results.
3. **Chat with LLM**: Use the "Chat with LLM" button to have an open-ended conversation with the AI.
4. **Download Results**: After generating ideas or chatting, use the "Download as PDF" button to save your results.

## Contributing

Contributions to the Idea Generator project are welcome! Please feel free to submit pull requests, create issues or spread the word.

## Acknowledgements

- Thanks to Groq for providing the LLM API.
- Special thanks to all the API providers that make this idea generator possible.
