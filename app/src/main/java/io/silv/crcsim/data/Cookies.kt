package io.silv.crcsim.data

import io.silv.crcsim.models.Rarity
import io.silv.crcsim.models.string


enum class Cookie(val id: String, val rarity: String) {
    //Common
    GingerBrave("GingerBrave", "c"),
    Muscle("Muscle", "c"),
    Strawberry("Strawberry", "c"),
    Wizard("Wizard", "c"),
    Beet("Beet", "c"),
    Ninja("Ninja","c"),
    Angel("Angel","c"),
    //Rare
    Princess("Princess","r"),
    Avocado("Avocado","r"),
    Knight("Knight","r"),
    Blackberry("Blackberry","r"),
    Devil("Devil","r"),
    Adventurer("Adventurer","r"),
    Pancake("Pancake","r"),
    Alchemist("Alchemist","r"),
    Cherry("Cherry","r"),
    Gumball("Gumball","r"),
    Carrot("Carrot","r"),
    Onion("Onion","r"),
    Clover("Clover","r"),
    Custard("Custard","r"),
    //Special
    Sonic("Sonic","s"),
    Tails("Tails","s"),
    RM("RM","s"),
    Jin("Jin","s"),
    Suga("Suga","s"),
    Jhope("Jhope","s"),
    Jimin("Jimin","s"),
    V("V","s"),
    Jungkook("Jungkook","s"),

    //Epic
    Darkchoco("Dark choco","e"),
    Yam("Yam","e"),
    Werewolf("Werewolf","e"),
    Kumiho("Kumiho","e"),
    Redvelvet("Red velvet","e"),
    Raspberry("Raspberry","e"),
    Malasauce("Malasauce","e"),
    Teaknight("Tea knight","e"),
    Crunchychip("Crunchy chip","e"),
    Schwarzwalder("Schwarzwalder","e"),
    MilkyWay("MilkyWay","e"),
    Made("Made","e"),
    Milk("Milk","e"),
    Strawberrycrepe("Strawberry Crepe","e"),
    MoonRabbit("Moon Rabbit","e"),
    Cocoa("Cocoa","e"),
    Wildberry("Wild berry","e"),
    Financier("Financier","e"),
    Lico("Lico","e"),
    Snowsugar("Snow sugar","e"),
    Espresso("Espresso","e"),
    Latte("Latte","e"),
    Mango("Mango","e"),
    SquidInk("SquidInk","e"),
    Pumpkinpie("Pumpkin pie","e"),
    Macaron("Macaron","e"),
    Rye("Rye","e"),
    Tigerlily("Tiger lily","e"),
    Pastry("Pastry","e"),
    Twizzlygummy("Twizzly Gummy","e"),
    Caramel("Caramel","e"),
    Chilipepper("Chili pepper","e"),
    Vampire("Vampire","e"),
    Blackraisin("Black raisin","e"),
    SorbetShark("Sorbet Shark","e"),
    Cherryblossom("Cherry blossom","e"),
    Poison("Poison","e"),
    Affogato("Affogato","e"),
    Captaincaviar("Captain caviar","e"),
    Pinecone("Pine cone","e"),
    Mintchoco("Mint choco","e"),
    Pome("Pome","e"),
    Almond("Almond","e"),
    Creampuff("Cream puff","e"),
    Fig("Fig","e"),
    Lilac("Lilac","e"),
    Parfait("Parfait","e"),
    Prophet("Prophet","e"),
    Cotton("Cotton","e"),
    Eclair("Eclair","e"),
    Candydiver("Candy diver","e"),
    Herb("Herb","e"),
    Sparkling("Sparkling","e"),
    Creamunicorn("Cream unicorn","e"),
    Carol("Carol","e"),

    //super epic
    Clottedcream("Clotted Cream", "s"),
    Sherbet("Sherbet", "s"),
    Oyster("Oyster", "s"),

    //ancient
    PureVanilla("Pure Vanilla", "l"),
    Hollyberry("Holly berry", "l"),
    Darkcacao("Dark cacao", "l"),

    //Legendary
    SeaFairy("Sea Fairy", "l"),
    Frostqueen("Frost Queen", "l"),
    Blackpearl("Black Pearl", "l"),
    Moonlight("Moonlight", "l"),
}

fun allCookies() = Cookie.values().toList()


fun List<Cookie>.filterRarity(rarity: Rarity) = this.filter {
    it.rarity == rarity.string()
}.ifEmpty { listOf(Cookie.GingerBrave) }


fun getAllCookieNames() = Cookie.values().map { it.id }
