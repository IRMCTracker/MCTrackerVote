# MCTrackerVote

IRMCTracker official plugin for handling player votes server-side

## Installation

Download the latest version from [releases](https://github.com/Alijkaz/MCTrackerVote/releases) or compile your own fork of plugin and put it in your server's ~/plugin folder 

## Config
Don't forget to update your **server_id** in config.yml on first installation
```yaml
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#                                                                                                         # 
#   __  __          _______                         _                   __      __          _             #
#  |  \/  |        |__   __|                       | |                  \ \    / /         | |            #
#  | \  / |   ___     | |     _ __    __ _    ___  | | __   ___   _ __   \ \  / /    ___   | |_    ___    #
#  | |\/| |  / __|    | |    | '__|  / _` |  / __| | |/ /  / _ \ | '__|   \ \/ /    / _ \  | __|  / _ \   #
#  | |  | | | (__     | |    | |    | (_| | | (__  |   <  |  __/ | |       \  /    | (_) | | |_  |  __/   #
#  |_|  |_|  \___|    |_|    |_|     \__,_|  \___| |_|\_\  \___| |_|        \/      \___/   \__|  \___|   #
#                                                                                                         #
#                                                                                          v 1.0.0 - beta #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #


# Permissions
# /vote = mctracker.commands.vote

# Your server ID
# You can get your server ID from IRMCTracker discord through ticket or our public API (If you know about it)
server_id: 0

# Main plugin prefix
# Set to "" for disabling prefix
prefix: "&c&lMC&b&lVote &c&l»&r "

# Commands that will get executed on player vote ( Bungee commands are not supported YET! )
# Placeholders:
#   - {player} = Player name
reward_commands:
  - "me Player {player} be server vote dad!"
  - "give {player} apple 1"

# Will send this message when player executes '/vote' command
# Placeholders:
# - {vote_url} = Your unique vote URL based on  server_id that you set above
# - {player} = Player name
vote_messages:
  - "&bSalam &7{player}"
  - "&bBaraye vote dadan be server bar rooye link zir click konid"
  - "&3Link Vote : &e{vote_url}"
  - "&bHar 24 saat yekbar mitonid vote bedid va jayeze begirid"

# No permission message
no_permission: "&c&lMC&b&lVote >> &cYou don't have permission."

# Player vote check cycle in minutes ( Do not change it if you don't know what is this )
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
<img src="https://discordapp.com/api/guilds/866287155641843722/widget.png?style=banner1" alt="Discord Banner 1"/>

https://discord.gg/YScSMwTFZq

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
All rights are reserved and it is not Open Source or Free. You cannot modify or redistribute this code without explicit permission from the copyright holder
