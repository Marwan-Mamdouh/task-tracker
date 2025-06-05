# Task Tracker CLI

A simple command-line task management application built with Spring Boot and Spring Shell. This tool allows you to manage your tasks directly from the terminal with an interactive shell interface.
I built it as a past from roadmap.sh projects here thier link: (roadMap.sh)[https://roadmap.sh/projects/task-tracker]

## Features

- ✅ Add new tasks
- 📝 Update task descriptions
- 🗑️ Delete tasks
- 📋 List all tasks or filter by status
- ✔️ Mark tasks as done
- 🔄 Mark tasks as in progress
- 💾 Persistent storage in JSON format
- 🎯 Interactive command-line interface

## System Requirements

### Minimum Requirements
- **Java**: OpenJDK 21 or Oracle JDK 21+
- **Memory**: 512 MB RAM
- **Storage**: 50 MB free disk space
- **Operating System**: Windows 10+, macOS 10.14+, or Linux (Ubuntu 18.04+)

### Recommended Requirements
- **Java**: Latest LTS version (Java 21)
- **Memory**: 1 GB RAM
- **Storage**: 100 MB free disk space

## Java Installation

### Check if Java is already installed
```bash
java -version
```

If Java is installed, you should see version information. If not, follow the installation steps below.

### Installing Java

#### Windows
1. **Download OpenJDK 21**:
   - Visit [Eclipse Temurin](https://adoptium.net/)
   - Download the Windows x64 MSI installer for Java 21
   
2. **Install**:
   - Run the downloaded MSI file
   - Follow the installation wizard
   - Make sure to check "Set JAVA_HOME variable" during installation

3. **Verify Installation**:
   ```cmd
   java -version
   javac -version
   ```

#### macOS
1. **Using Homebrew** (recommended):
   ```bash
   brew install openjdk@21
   ```

2. **Using Official Installer**:
   - Download from [Eclipse Temurin](https://adoptium.net/)
   - Install the PKG file

3. **Verify Installation**:
   ```bash
   java -version
   ```

#### Linux (Ubuntu/Debian)
```bash
# Update package index
sudo apt update

# Install OpenJDK 21
sudo apt install openjdk-21-jdk

# Verify installation
java -version
javac -version
```

#### Linux (CentOS/RHEL/Fedora)
```bash
# For CentOS/RHEL
sudo yum install java-21-openjdk-devel

# For Fedora
sudo dnf install java-21-openjdk-devel

# Verify installation
java -version
```

## Installation & Usage

### Option 1: Run the JAR file directly (Easiest)
1. **Download the JAR file** from the releases section
2. **Run the application**:
   ```bash
   java -jar task-tracker-1.0.jar
   ```

### Option 2: Build from source
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/task-tracker.git
   cd task-tracker
   ```

2. **Build the project**:
   ```bash
   # On Windows
   ./mvnw.cmd clean package
   
   # On macOS/Linux
   ./mvnw clean package
   ```

3. **Run the application**:
   ```bash
   java -jar target/task-tracker-1.0.jar
   ```

## Available Commands

Once the application starts, you'll see an interactive shell prompt. Here are the available commands:

### Basic Task Management
```bash
# Add a new task
add "Learn Spring Boot"

# List all tasks
list

# List tasks by status
list todo
list in-progress
list done

# Update a task description
update 1 "Learn Spring Boot and Spring Shell"

# Delete a task
delete 1
```

### Task Status Management
```bash
# Mark task as done
mark-done 1
done 1

# Mark task as in progress
mark-in-progress 1
mark-progress 1
progress 1
```

### Other Commands
```bash
# Get help
help

# Exit the application
exit
quit
```

## Task Status Types

- **TODO**: Newly created tasks (default status)
- **IN_PROGRESS**: Tasks currently being worked on
- **DONE**: Completed tasks

## Data Storage

Tasks are automatically saved to a JSON file (`data/tasks.json`) in the application directory. The data persists between application runs.

### Sample Task Data Structure
```json
[
  {
    "taskId": 1,
    "description": "Learn Spring Boot",
    "status": "TODO",
    "createdAt": [2025, 6, 3],
    "updatedAt": null
  }
]
```

## Configuration

You can customize the data file location by modifying `src/main/resources/application.yml`:

```yaml
app:
  data:
    file:
      path: "data/tasks.json"  # Change this path as needed
```

## Development

### Prerequisites for Development
- Java 21
- Maven 3.9.9+ (included via Maven Wrapper)

### Build Commands
```bash
# Clean and compile
./mvnw clean compile

# Run tests
./mvnw test

# Package into JAR
./mvnw package

# Run the application in development
./mvnw spring-boot:run
```

### Project Structure
```
src/
├── main/
│   ├── java/com/marwan/dev/task_tracker/
│   │   ├── commands/          # Shell command handlers
│   │   ├── config/           # Configuration classes
│   │   ├── exceptions/       # Custom exceptions
│   │   ├── model/            # Task model and enums
│   │   ├── repository/       # Data access layer
│   │   ├── services/         # Business logic
│   │   └── Application.java  # Main application class
│   └── resources/
│       └── application.yml   # Application configuration
└── test/                     # Unit tests
```

## Troubleshooting

### Common Issues

1. **"Command not found" error**:
   - Make sure Java is properly installed and in your PATH
   - Try using the full path to java: `/path/to/java -jar task-tracker-1.0.jar`

2. **Permission denied error**:
   ```bash
   chmod +x mvnw  # Make Maven wrapper executable
   ```

3. **Port or file access issues**:
   - Ensure the application has write permissions in the directory
   - Check if another instance is already running

4. **Java version compatibility**:
   - This application requires Java 21+
   - Check your Java version with `java -version`

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE).

## Support

If you encounter any issues or have questions:
1. Check the [Issues](https://github.com/yourusername/task-tracker/issues) section
2. Create a new issue if your problem isn't already reported
3. Provide detailed information about your environment and the steps to reproduce the issue

---

**Happy Task Tracking! 🚀**
