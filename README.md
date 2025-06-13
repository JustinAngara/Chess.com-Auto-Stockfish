# ♟️ Chess.com Auto Stockfish Assistant

[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/JustinAngara/Chess.com-Auto-Stockfish)

## 🎯 Purpose

This project integrates the Stockfish chess engine with the Chess.com web interface to provide real-time positional analysis and optional automation. Built with Java and JNA, the application reads screen data, parses the board state, invokes Stockfish for move suggestions, and (optionally) performs GUI-based move automation.

The goal is to demonstrate how classical engine logic (Stockfish) can be coupled with modern GUI automation and image analysis to aid decision-making in online environments, emphasizing the need for responsible AI-assisted tools in competitive platforms.

## ⚠️ Ethical Disclaimer

This project was created for educational, personal-use, and research purposes. It is not intended to violate the terms of service of any platform or promote dishonest behavior. Users are expected to use this tool only in contexts where assistance is permitted (e.g., analysis, casual games, self-improvement). Misuse in competitive environments may lead to platform penalties or bans.

## ✨ Features

- Stealthy real-time board recognition via screen capture.
- FEN string generation from board state.
- Stockfish integration using UCI protocol.
- Optional automated move input with GUI simulation.
- Configurable hotkeys (e.g., F8 for analysis, F9 for move).
- Move logging and time-stamped evaluations.
- Analysis-only, semi-automated, or fully automated modes.

## 🔧 Tech Stack

| Component              | Technology                            |
|------------------------|----------------------------------------|
| Core Language          | Java                                   |
| Chess Engine           | Stockfish (UCI)                        |
| Automation Layer       | Java AWT Robot, JNA for key/mouse      |
| Screen Parsing         | Java ImageIO + Custom OCR/Mapping      |
| UI (optional)          | Java Swing (control panel)             |
| Build System           | Maven                                  |
| OS Support             | Windows (optimized)                    |

## 🚀 Running the Project

### Prerequisites

- Java 17+
- Stockfish (must be downloaded separately)
- Apache Maven
- Windows OS

### 1. Clone and Configure

```bash
git clone https://github.com/JustinAngara/Chess.com-Auto-Stockfish.git
cd Chess.com-Auto-Stockfish
