# MCTrackerVote

IRMCTracker official plugin for handling player votes server-side

## Installation

Download the latest version from [releases](https://github.com/Alijkaz/MCTrackerVote/releases) or compile your own fork of plugin and put it in your server's ~/plugin folder 

## Config
Don't forget to update your **server_id** in config.yml on first installation
```yaml
# -----------------------------
#        MCTrackerVote
# -----------------------------
#  =  Perms/Commands =
# - /vote [ mctracker.commands.vote ]
# -----------------------------

# You can get your server id from IRMCTracker discord through ticket or our public API (If you know about it)
server_id: 0

# Main plugin prefix
prefix: "&c&lMC&b&lVote &c&l»&r "

# Commands that will get executed on player vote [ BUNGEE COMMANDS ARE NOT SUPPORTED FOR NOW ]
# Placeholders:
#   - {player} = Player name
reward_commands:
  - "me Player {player} be server vote dad!"
  - "give {player} apple 1"

# Will send this message on execution of /vote
# Placeholders:
# - {vote_url} = Your unique vote URL based on  server_id that you set above
# - {player} = Player name
vote_messages:
  - "&bSalam &9{player}"
  - "&bBaraye vote dadan be server kafie ke vared link zir beshid"
  - "&bhar 24 saat username khodetoon ro vared konid!"
  - "&3Address: &3&l{vote_url}"
  - "Ta jayze daryaft konid"

# No permission message
no_permission: "&c&lMC&b&lVote >> insufficient permissions"

# Player vote check cycle in minutes [ DON'T TOUCH IF YOU DON'T KNOW WHAT YOU'RE DOING ]
cycle: 2
```
## Before using the plugin
Tested MineCraft Version
   - Paper 1.8.8 (Java 1.8 / Java 1.11)
   - Paper 1.12.2 (Java 1.8 / Java 1.11)
   - Paper 1.13.2 (Java 1.11)
   - Paper 1.16.5 (Java 1.11)
   - Paper 1.17.1 (Java 1.16)

## You have any issuess / suggestions??

We're always here to solve your problems or convert your ideas into codes! You can create issues on our official Github Repo or contact us through our discord server

## Our Discord
https://discord.gg/YScSMwTFZq
<img src="https://discordapp.com/api/guilds/866287155641843722/widget.png?style=banner1" alt="Discord Banner 1"/>


## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
All rights are reserved and it is not Open Source or Free. You cannot modify or redistribute this code without explicit permission from the copyright holder
