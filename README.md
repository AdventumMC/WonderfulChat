![Header](https://i.imgur.com/DbJhus1.png)

WonderfulChat is an API/Plugin which is made to customize and facilitate Minecraft's chat. It is depending of [Spigot](https://www.spigotmc.org/)'s API.  

If you do appreciate our work and want to support us. You can make a [donation to the server](http://shop.adventum.fr/) in the donation section *(or contact me on Discord Shyrogan#0955 if the website is not available)*.
Keep in mind this is only a **pre-release**. There might be a lot of glitches and feature to come later.

# Getting started

### Install
Download the plugin file on our [releases page](https://github.com/AdventumMC/WonderfulChat/releases) then place it into your server /plugins/ directory.
Restart or reload your server and you're ready to go!

### About channels

Channels are just an addition to Minecraft's base chat. The plugin does not actually provide a way to manage default's minecraft chat.

Each channels needs a minimal amount of information to work correctly. Otherwise, an error will be send inside of the console.  
These are the following:
````
    name: "MyName"
    prefix: "&aMyPrefix"
    marker: "#"
````
Take a look at our default configuration, everything is specified inside.

### Conditions

Conditions are made to reduce visibility of a chat like for a Staff channel or specific world channel.
There are only 2 conditions available at the moment but you can ask for more inside of the resource's discussion on Spigot.
````
world=world
permission=chat.permission
````

For example if I want to make a chat for players who have permission ``staff.chat``, I just need to specify
````
    conditions: "permission=staff.chat"
````
and we're ready to go.

WonderfulChat also supports multiple condition using ``;`` between each of them like in this example:
````
    conditions: "permission=staff.chat; world=MyWorld"
````

### Thanks for using our plugin !

We're really proud to share this work to Spigot's community and we hope people are going to use it as we do.
If you want to talk or thank us for our work, [join our discord](https://discord.gg/ZQbz3RF) !