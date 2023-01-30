package io.silv.crcsim.data

import io.silv.crcsim.models.Rarity


enum class Cookie(val id: String, val rarity: Rarity) {
    //Common
    GingerBrave("GingerBrave", Rarity.Common),
    Muscle("Muscle", Rarity.Common),
    Strawberry("Strawberry", Rarity.Common),
    Wizard("Wizard", Rarity.Common),
    Beet("Beet", Rarity.Common),
    Ninja("Ninja",Rarity.Common),
    Angel("Angel",Rarity.Common),
    //Rare
    Princess("Princess",Rarity.Rare),
    Avocado("Avocado",Rarity.Rare),
    Knight("Knight",Rarity.Rare),
    Blackberry("Blackberry",Rarity.Rare),
    Devil("Devil",Rarity.Rare),
    Adventurer("Adventurer",Rarity.Rare),
    Pancake("Pancake",Rarity.Rare),
    Alchemist("Alchemist",Rarity.Rare),
    Cherry("Cherry",Rarity.Rare),
    Gumball("Gumball",Rarity.Rare),
    Carrot("Carrot",Rarity.Rare),
    Onion("Onion",Rarity.Rare),
    Clover("Clover",Rarity.Rare),
    Custard("Custard",Rarity.Rare),
    //Special
    Sonic("Sonic",Rarity.Special),
    Tails("Tails",Rarity.Special),
    RM("RM",Rarity.Special),
    Jin("Jin",Rarity.Special),
    Suga("Suga",Rarity.Special),
    Jhope("J_Hope",Rarity.Special),
    Jimin("Jimin",Rarity.Special),
    V("V",Rarity.Special),
    Jungkook("Jung_Kook",Rarity.Special),

    //Epic
    Darkchoco("Dark_Choco",Rarity.Epic),
    Yam("Purple_Yam",Rarity.Epic),
    Werewolf("Werewolf",Rarity.Epic),
    Kumiho("Kumiho",Rarity.Epic),
    Redvelvet("Red_Velvet",Rarity.Epic),
    Raspberry("Raspberry",Rarity.Epic),
    Malasauce("Mala_Sauce",Rarity.Epic),
    Teaknight("Tea_Knight",Rarity.Epic),
    Crunchychip("Crunchy_Chip",Rarity.Epic),
    Schwarzwalder("Schwarzwalder",Rarity.Epic),
    MilkyWay("Milky_Way",Rarity.Epic),
    Made("Made",Rarity.Epic),
    Milk("Milk",Rarity.Epic),
    Strawberrycrepe("Strawberry_Crepe",Rarity.Epic),
    MoonRabbit("Moon_Rabbit",Rarity.Epic),
    Cocoa("Cocoa",Rarity.Epic),
    Wildberry("Wildberry",Rarity.Epic),
    Financier("Financier",Rarity.Epic),
    Lico("Lico",Rarity.Epic),
    Snowsugar("Snow_Sugar",Rarity.Epic),
    Espresso("Espresso",Rarity.Epic),
    Latte("Latte",Rarity.Epic),
    Mango("Mango",Rarity.Epic),
    SquidInk("Squid_Ink",Rarity.Epic),
    Pumpkinpie("Pumpkin_Pie",Rarity.Epic),
    Macaron("Macaron",Rarity.Epic),
    Rye("Rye",Rarity.Epic),
    Tigerlily("Tiger_Lily",Rarity.Epic),
    Pastry("Pastry",Rarity.Epic),
    Twizzlygummy("Twizzly_Gummy",Rarity.Epic),
    Caramel("Caramel",Rarity.Epic),
    Chilipepper("Chili_Pepper",Rarity.Epic),
    Vampire("Vampire",Rarity.Epic),
    Blackraisin("Black_Raisin",Rarity.Epic),
    SorbetShark("Sorbet_Shark",Rarity.Epic),
    Cherryblossom("Cherry_Blossom",Rarity.Epic),
    Poisonmushroom("Poison_Mushroom",Rarity.Epic),
    Affogato("Affogato",Rarity.Epic),
    Captaincaviar("Captain_Caviar",Rarity.Epic),
    Pinecone("Pinecone",Rarity.Epic),
    Mintchoco("Mint_Choco",Rarity.Epic),
    Pomegranate("Pomegranate",Rarity.Epic),
    Almond("Almond",Rarity.Epic),
    Creampuff("Cream_Puff",Rarity.Epic),
    Fig("Fig",Rarity.Epic),
    Lilac("Lilac",Rarity.Epic),
    Parfait("Parfait",Rarity.Epic),
    Prophet("Prophet",Rarity.Epic),
    Cotton("Cotton",Rarity.Epic),
    Eclair("Eclair",Rarity.Epic),
    Candydiver("Candy_Diver",Rarity.Epic),
    Herb("Herb",Rarity.Epic),
    Sparkling("Sparkling",Rarity.Epic),
    Creamunicorn("Cream_Unicorn",Rarity.Epic),
    Carol("Carol",Rarity.Epic),

    //super epic
    Clottedcream("Clotted_Cream", Rarity.Special),
    Sherbet("Sherbet", Rarity.Special),
    Oyster("Oyster", Rarity.Special),

    //ancient
    PureVanilla("Pure_Vanilla", Rarity.Legendary),
    Hollyberry("Hollyberry", Rarity.Legendary),
    Darkcacao("Dark_Cacao", Rarity.Legendary),

    //Legendary
    SeaFairy("Sea_Fairy", Rarity.Legendary),
    Frostqueen("Frost_Queen", Rarity.Legendary),
    Blackpearl("Black_Pearl", Rarity.Legendary),
    Moonlight("Moonlight", Rarity.Legendary),
}

fun allCookies() = Cookie.values().toList()


fun List<Cookie>.filterRarity(rarity: Rarity) = this.filter {
    it.rarity == rarity
}.ifEmpty { listOf(Cookie.GingerBrave) }


fun getAllCookieNames() = Cookie.values().map { it.id }
