♟️ Chess.com Auto Stockfish Assistant


🎯 Purpose
This project integrates the Stockfish chess engine with the Chess.com web interface to provide real-time positional analysis and optional automation. Built with Java and JNA, the application reads screen data, parses the board state, invokes Stockfish for move suggestions, and (optionally) performs GUI-based move automation.

The goal is to demonstrate how classical engine logic (Stockfish) can be coupled with modern GUI automation and image analysis to aid decision-making in online environments, emphasizing the need for responsible AI-assisted tools in competitive platforms.

⚠️ Ethical Disclaimer
This project was created for educational, personal-use, and research purposes. It is not intended to violate the terms of service of any platform or promote dishonest behavior. Users are expected to use this tool only in contexts where assistance is permitted (e.g., analysis, casual games, self-improvement). Misuse in competitive environments may lead to platform penalties or bans.

✨ Features
Board Recognition via OCR/Pixel Mapping: Detects the current chess board state using a pixel-map or OCR to identify piece locations and player turns.

FEN Generation: Converts the visual state of the game into a FEN (Forsyth-Edwards Notation) string to feed into Stockfish.

Stockfish Integration: Uses the native Stockfish engine via command-line or UCI to suggest the best move from the current board state.

Optional Move Automation: Simulates GUI input to execute the best move on Chess.com (disabled by default).

Hotkey Controls: Toggle analysis or move automation with configurable hotkeys (e.g., F8 to analyze, F9 to move).

Move History Logging: Saves suggested moves with timestamps and evaluation scores to a local file for later review.

Configurable Play Modes: Supports human-assist, full automation, or analysis-only modes.

🔧 Tech Stack
Component	Technology
Core Language	Java
Chess Engine	Stockfish (UCI)
Automation Layer	Java AWT Robot, JNA for key/mouse
Screen Parsing	Java ImageIO + Custom OCR/Mapping
UI (if any)	Java Swing for control panel (optional)
Build System	Maven
OS Support	Windows (optimized)

🚀 Running the Project
Prerequisites
Java 17+

Stockfish (must be downloaded separately)

Apache Maven

Windows OS (recommended due to JNA/UI assumptions)

1. Clone and Configure
bash
Copy
Edit
git clone https://github.com/JustinAngara/Chess.com-Auto-Stockfish.git
cd Chess.com-Auto-Stockfish
2. Setup Stockfish Path
Modify the following line in StockfishEngine.java:

java
Copy
Edit
private static final String ENGINE_PATH = "C:/path/to/stockfish.exe"; // <-- Replace this
3. Build the Project
bash
Copy
Edit
mvn clean package
4. Run the Application
bash
Copy
Edit
java -jar target/stockfish-assist-1.0-SNAPSHOT.jar
🖥️ How It Works
Board Capture: Grabs the screen region of the live Chess.com board and maps it into a 2D grid.

Piece Detection: Uses color/pattern heuristics or OCR to identify pieces on each square.

FEN Conversion: Constructs a FEN string from the current board state.

Stockfish Call: Sends the FEN to Stockfish using UCI commands and parses the best move output.

Action (Optional): If automation is enabled, moves the mouse and simulates click/drag to perform the best move on the board.

🛠️ Configuration
All runtime behavior is controlled via config.properties (or inline constants in source):

Setting	Description
enginePath	Path to Stockfish binary
autoMove	true to enable auto-move, false to disable
hotkeyAnalyze	Hotkey to trigger analysis
hotkeyMove	Hotkey to trigger automated move
scanRegionX/Y/Width/Height	Coordinates of the board on screen

💡 Example Workflow
Launch the Chess.com game in a browser.

Run the Java application.

Press F8 to analyze — Stockfish will read the board and suggest a move.

Press F9 (if enabled) to auto-perform the suggested move on the board.

📄 Example Output (CLI)
swift
Copy
Edit
[INFO] Detected board FEN: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
[STOCKFISH] Best Move: e2e4 | Eval: +0.25
📦 Directory Structure
swift
Copy
Edit
/src/main/java/com/stockfishbot
  ├── Main.java
  ├── BoardScanner.java
  ├── FENBuilder.java
  ├── StockfishEngine.java
  ├── AutoMover.java
  └── HotkeyManager.java

/configs/
  └── config.properties

/logs/
  └── move_history.log
🧠 Potential Extensions
Chrome-specific pixel mappings for more robust board reading.

ML-based piece recognition (e.g., CNN classifier).

Drag-and-drop simulation improvements.

Opponent move prediction models.

Support for other sites (Lichess, FICS).

🛡️ Limitations
Board recognition is brittle and may break with layout/theme changes.

Not resilient to anti-cheat detection (use responsibly).

Requires consistent screen resolution and browser zoom.

📢 Final Note
This tool is a proof of concept demonstrating real-time engine feedback via GUI parsing. It should be used responsibly and never in competitive scenarios where outside assistance is prohibited.
