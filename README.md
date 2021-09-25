# MCTrackerVote

IRMCTracker official plugin for handling player votes server-side

## Documentations
You can find our documentations [here](https://docs.mctracker.ir/mctrackervote-plugin/overview)

## Downloads

You can download the latest build from our [jenkins server](https://builds.alijk.ir/job/MCTrackerVote/) 

Or obviously you can compile your own fork of plugin and put it in your server's ~/plugin folder 

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
#                                                                         v 1.4.0 - production #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

# You can find documentations in below url:
# https://docs.mctracker.ir/mctrackervote-plugin/overview
# If you have any problems/suggestions please contact us through our discord server

# » Permissions «
# "/vote - mctracker.commands.vote"
# "/tracker reload - mctracker.commands.reload"

# Your server ID
# You can get your server ID from IRMCTracker discord through ticket or our public API or McTracker.iR Website URL (If you know about it)
server_id: 0

# Main plugin's Prefix
# Set to "" for disabling prefix
prefix: "&a&lMCTRACKER &b&l»&r "

# Actions on player vote
# ALERT: BUNGEECORD COMMANDS ARE NOT SUPPORTED YET!
#
# » Actions «
#   - [console] = This command will be executed from console
#   - [player] = This command will be executed from player
#   - [message] = This message will be sent to player
#
# » PlaceHolders «
#   - {player} = Player name
reward_actions:
  - "[message] &a{player}&e, Shoma &bVote&e Dadid Va Jayeze Gereftid!"
  - "[console] me Player {player} be server vote dad!"
  - "[console] give {player} apple 1"


# Will send this message when player executes '/vote' command
# » PlaceHolders «
# - {vote_url} = Your unique vote URL based on  server_id that you set above
# - {player} = Player name
vote_messages:
  - "&bHi &a{player}"
  - "&eBaraye Vote Dadan Be Server Az Link Zir Estefade Konid &7(1 Vote per Day)"
  - "&c{vote_url}"

# No permission message
no_permission: "&c&lMCTRACKER: &fError!, You don't have permission."

# Player vote check cycle in minutes ( Do not change it if you don't know what is this )
cycle: 1
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

Pull requests are welcomed. For major changes, please open an issue first to discuss what you would like to change.

## License
All rights are reserved and it is not Open Source or Free. You cannot modify or redistribute this code without explicit permission from the copyright holder
