# SafeServer

SafeServer is a Fabric mod for Minecraft servers that adds a simple password-based authentication system to enhance server security.

## Features

- Requires players to set a password and log in before playing
- Prevents unauthorized access to operator privileges
- Keeps players in spectator mode until they log in
- Teleports players back to their join position if they haven't logged in

## Installation

1. Make sure you have Fabric installed on your Minecraft server.
2. Download the SafeServer mod JAR file.
3. Place the JAR file in your server's `mods` folder.
4. Restart your server.

## Usage

### For Players

- When joining the server for the first time:
    1. Use `/setPassword <your_password>` to set your password.
    2. Use `/login <your_password>` to log in and start playing.

- For subsequent logins:
    - Use `/login <your_password>` to log in.

### For Server Operators

- Operator status is temporarily removed when joining the server.
- Log in using your password to regain operator privileges.

## Configuration

- Passwords are stored in the `passwords.properties` file in the server root directory.
- **Warning:** Passwords are stored in plain text. Consider using additional security measures.

## Commands

- `/setPassword <password>`: Set your password (can only be done once)
- `/login <password>`: Log in to the server

## Notes

- Players are kept in spectator mode and at their join position until they log in.
- Passwords cannot be changed after being set. Server administrators would need to manually edit or delete entries in the `passwords.properties` file if needed.