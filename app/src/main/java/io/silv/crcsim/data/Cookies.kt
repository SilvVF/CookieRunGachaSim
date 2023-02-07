package io.silv.crcsim.data

import io.silv.crcsim.models.Rarity

enum class Cookie(val id: String, val rarity: Rarity, val imageUrl: String) {
    //Common
    GingerBrave("GingerBrave", Rarity.Common, gingerBraveUrl),
    Muscle("Muscle", Rarity.Common, muscleUrl),
    Strawberry("Strawberry", Rarity.Common, strawberryUrl),
    Wizard("Wizard", Rarity.Common, wizardUrl),
    Beet("Beet", Rarity.Common, beetUrl),
    Ninja("Ninja",Rarity.Common, ninjaUrl),
    Angel("Angel",Rarity.Common, angelUrl),
    //Rare
    Princess("Princess",Rarity.Rare, princessUrl),
    Avocado("Avocado",Rarity.Rare, avocadoUrl),
    Knight("Knight",Rarity.Rare, knightUrl),
    Blackberry("Blackberry",Rarity.Rare, blackberryUrl),
    Devil("Devil",Rarity.Rare, devilUrl),
    Adventurer("Adventurer",Rarity.Rare, adventurerUrl),
    Pancake("Pancake",Rarity.Rare, pancakeUrl),
    Alchemist("Alchemist",Rarity.Rare, alchemistUrl),
    Cherry("Cherry",Rarity.Rare, cherryUrl),
    Gumball("Gumball",Rarity.Rare, gumballUrl),
    Carrot("Carrot",Rarity.Rare, carrotUrl),
    Onion("Onion",Rarity.Rare, onionUrl),
    Clover("Clover",Rarity.Rare, cloverurl),
    Custard("Custard",Rarity.Rare, custardUrl),
    //Special
    Sonic("Sonic",Rarity.Special, sonicUrl),
    Tails("Tails",Rarity.Special, tailsUrl),
    RM("RM",Rarity.Special, rmUrl),
    Jin("Jin",Rarity.Special, jinUrl),
    Suga("Suga",Rarity.Special, sugaUrl),
    Jhope("J_Hope",Rarity.Special, jHopeUrl),
    Jimin("Jimin",Rarity.Special, jiminUrl),
    V("V",Rarity.Special, vUrl),
    Jungkook("Jung_Kook",Rarity.Special, jungKookUrl),
    //Epic
    Darkchoco("Dark_Choco",Rarity.Epic, darkChocoUrl),
    Yam("Purple_Yam",Rarity.Epic, yamUrl),
    Werewolf("Werewolf",Rarity.Epic, werewolfUrl),
    Kumiho("Kumiho",Rarity.Epic, kumihoUrl),
    Redvelvet("Red_Velvet",Rarity.Epic, redVelvetUrl),
    Raspberry("Raspberry",Rarity.Epic, raspberryUrl),
    Malasauce("Mala_Sauce",Rarity.Epic, malaSauceUrl),
    Teaknight("Tea_Knight",Rarity.Epic, teaKnightUrl),
    Crunchychip("Crunchy_Chip",Rarity.Epic, crunchyChipUrl),
    Schwarzwalder("Schwarzwalder",Rarity.Epic, schwarzwalderUrl),
    MilkyWay("Milky_Way",Rarity.Epic, milkyWayUrl),
    Made("Made",Rarity.Epic, madeUrl),
    Milk("Milk",Rarity.Epic, milkUrl),
    Strawberrycrepe("Strawberry_Crepe",Rarity.Epic, strawberryCrepeUrl),
    MoonRabbit("Moon_Rabbit",Rarity.Epic, moonRabbitUrl),
    Cocoa("Cocoa",Rarity.Epic, cocoaUrl),
    Wildberry("Wildberry",Rarity.Epic, wildberryUrl),
    Financier("Financier",Rarity.Epic, financierUrl),
    Lico("Lico",Rarity.Epic, licoUrl),
    Snowsugar("Snow_Sugar",Rarity.Epic, snowsugarUrl),
    Espresso("Espresso",Rarity.Epic, espressoUrl),
    Latte("Latte",Rarity.Epic, latteUrl),
    Mango("Mango",Rarity.Epic, mangoUrl),
    SquidInk("Squid_Ink",Rarity.Epic, squidInkUrl),
    Pumpkinpie("Pumpkin_Pie",Rarity.Epic, pumpkinPieUrl),
    Macaron("Macaron",Rarity.Epic, macaroonUrl),
    Rye("Rye",Rarity.Epic, ryeUrl),
    Tigerlily("Tiger_Lily",Rarity.Epic, tigerLilyUrl),
    Pastry("Pastry",Rarity.Epic, pastryUrl),
    Twizzlygummy("Twizzly_Gummy",Rarity.Epic, twizzlyGummyUrl),
    Caramel("Caramel",Rarity.Epic, caramelUrl),
    Chilipepper("Chili_Pepper",Rarity.Epic, chilipepperUrl),
    Vampire("Vampire",Rarity.Epic, vampierUrl),
    Blackraisin("Black_Raisin",Rarity.Epic, blackraisinUrl),
    SorbetShark("Sorbet_Shark",Rarity.Epic, sorbetUrl),
    Cherryblossom("Cherry_Blossom",Rarity.Epic, cherryBlossomUrl),
    Poisonmushroom("Poison_Mushroom",Rarity.Epic, poisonMushroomUrl),
    Affogato("Affogato",Rarity.Epic, affogatoUrl),
    Captaincaviar("Captain_Caviar",Rarity.Epic, captainCavUrl),
    Pinecone("Pinecone",Rarity.Epic, pineconeUrl),
    Mintchoco("Mint_Choco",Rarity.Epic, mintchocoUrl),
    Pomegranate("Pomegranate",Rarity.Epic, pomegranateUrl),
    Almond("Almond",Rarity.Epic, almondUrl),
    Creampuff("Cream_Puff",Rarity.Epic, creampuffUrl),
    Fig("Fig",Rarity.Epic, figUrl),
    Lilac("Lilac",Rarity.Epic, lilacUrl),
    Parfait("Parfait",Rarity.Epic, parfaitUrl),
    Prophet("Prophet",Rarity.Epic, prophetUrl),
    Cotton("Cotton",Rarity.Epic, cottonUrl),
    Eclair("Eclair",Rarity.Epic, eclairUrl),
    Candydiver("Candy_Diver",Rarity.Epic, candyDiverUrl),
    Herb("Herb",Rarity.Epic, herbUrl),
    Sparkling("Sparkling",Rarity.Epic, sparklingUrl),
    Creamunicorn("Cream_Unicorn",Rarity.Epic, creamUnicornUrl),
    Carol("Carol",Rarity.Epic, carolUrl),
    //super epic
    Clottedcream("Clotted_Cream", Rarity.Special, clottedCreamUrl),
    Sherbet("Sherbet", Rarity.Special, sherbertUrl),
    Oyster("Oyster", Rarity.Special, oysterUrl),
    //ancient
    PureVanilla("Pure_Vanilla", Rarity.Legendary, pureVanillaUrl),
    Hollyberry("Hollyberry", Rarity.Legendary, hollyBerryUrl),
    Darkcacao("Dark_Cacao", Rarity.Legendary, darkcacaoUrl),
    //Legendary
    SeaFairy("Sea_Fairy", Rarity.Legendary, seaFairyUrl),
    Frostqueen("Frost_Queen", Rarity.Legendary, frostQueenUrl),
    Blackpearl("Black_Pearl", Rarity.Legendary, blackPearlUrl),
    Moonlight("Moonlight", Rarity.Legendary, moonlightUrl),
}

fun allCookies() = Cookie.values().toList()


fun List<Cookie>.filterRarity(rarity: Rarity) = this.filter {
    it.rarity == rarity
}.ifEmpty { listOf(Cookie.GingerBrave) }


fun getAllCookieNames() = Cookie.values().map { it.id }
