package io.silv.crcsim.data

import io.silv.crcsim.models.Rarity

enum class Cookie(
    val id: String,
    val rarity: Rarity,
    val imageUrl: String,
    val soulstoneUrl: String,
   // val gachaBgUrl: String,
) {
    //Common
    GingerBrave("GingerBrave", Rarity.Common, gingerBraveUrl, gingerbraveSSUrl),
    Muscle("Muscle", Rarity.Common, muscleUrl, muscleSSUrl),
    Strawberry("Strawberry", Rarity.Common, strawberryUrl, strawberrySSUrl),
    Wizard("Wizard", Rarity.Common, wizardUrl, wizardSSUrl),
    Beet("Beet", Rarity.Common, beetUrl, beetSSUrl),
    Ninja("Ninja",Rarity.Common, ninjaUrl, ninjaSSUrl),
    Angel("Angel",Rarity.Common, angelUrl, angelSSUrl),
    //Rare
    Princess("Princess",Rarity.Rare, princessUrl, princessSSUrl),
    Avocado("Avocado",Rarity.Rare, avocadoUrl, avocadoSSUrl),
    Knight("Knight",Rarity.Rare, knightUrl, knightSSUrl),
    Blackberry("Blackberry",Rarity.Rare, blackberryUrl,  blackberrySSUrl),
    Devil("Devil",Rarity.Rare, devilUrl, devilSSUrl),
    Adventurer("Adventurer",Rarity.Rare, adventurerUrl, adventurerSSUrl),
    Pancake("Pancake",Rarity.Rare, pancakeUrl, pancakeSSUrl),
    Alchemist("Alchemist",Rarity.Rare, alchemistUrl, alchemistSSUrl),
    Cherry("Cherry",Rarity.Rare, cherryUrl, cherrySSUrl),
    Gumball("Gumball",Rarity.Rare, gumballUrl, gumballSSUrl),
    Carrot("Carrot",Rarity.Rare, carrotUrl, carrotSSUrl),
    Onion("Onion",Rarity.Rare, onionUrl, onionSSUrl),
    Clover("Clover",Rarity.Rare, cloverurl, cloverSSUrl),
    Custard("Custard",Rarity.Rare, custardUrl, custardSSUrl),
    //Special
    Sonic("Sonic",Rarity.Special, sonicUrl, sonicSSUrl),
    Tails("Tails",Rarity.Special, tailsUrl, tailsSSUrl),
    RM("RM",Rarity.Special, rmUrl, rmSSUrl),
    Jin("Jin",Rarity.Special, jinUrl, jinSSUrl),
    Suga("Suga",Rarity.Special, sugaUrl, sugaSSUrl),
    Jhope("J_Hope",Rarity.Special, jHopeUrl, jHopeSSUrl),
    Jimin("Jimin",Rarity.Special, jiminUrl, jiminSSUrl),
    V("V",Rarity.Special, vUrl, vSSUrl),
    Jungkook("Jung_Kook",Rarity.Special, jungKookUrl, jungkookSSUrl),
    //Epic
    Darkchoco("Dark_Choco",Rarity.Epic, darkChocoUrl, darkChocoSSUrl),
    Yam("Purple_Yam",Rarity.Epic, yamUrl, yamSSUrl),
    Werewolf("Werewolf",Rarity.Epic, werewolfUrl, werewolfSSUrl),
    Kumiho("Kumiho",Rarity.Epic, kumihoUrl, kumihoSSUrl),
    Redvelvet("Red_Velvet",Rarity.Epic, redVelvetUrl, redvelvetSSUrl),
    Raspberry("Raspberry",Rarity.Epic, raspberryUrl, raspberrySSUrl),
    Malasauce("Mala_Sauce",Rarity.Epic, malaSauceUrl, malasauceSSUrl),
    Teaknight("Tea_Knight",Rarity.Epic, teaKnightUrl, teaknightSSUrl),
    Crunchychip("Crunchy_Chip",Rarity.Epic, crunchyChipUrl, crunchychipSSUrl),
    Schwarzwalder("Schwarzwalder",Rarity.Epic, schwarzwalderUrl, schwarzwalderSSUrl),
    MilkyWay("Milky_Way",Rarity.Epic, milkyWayUrl, milkywaySSUrl),
    Made("Made",Rarity.Epic, madeUrl, madeSSUrl),
    Milk("Milk",Rarity.Epic, milkUrl, milkSSUrl),
    Strawberrycrepe("Strawberry_Crepe",Rarity.Epic, strawberryCrepeUrl, strawberrycrepeSSUrl),
    MoonRabbit("Moon_Rabbit",Rarity.Epic, moonRabbitUrl, moonRabbitSSurl),
    Cocoa("Cocoa",Rarity.Epic, cocoaUrl, cocoaSSUrl),
    Wildberry("Wildberry",Rarity.Epic, wildberryUrl, wildberrySSUrl),
    Financier("Financier",Rarity.Epic, financierUrl, financierSSUrl),
    Lico("Lico",Rarity.Epic, licoUrl, licoSSUrl),
    Snowsugar("Snow_Sugar",Rarity.Epic, snowsugarUrl, snowsugarSSUrl),
    Espresso("Espresso",Rarity.Epic, espressoUrl, espressoSSUrl),
    Latte("Latte",Rarity.Epic, latteUrl, latteSSUrl),
    Mango("Mango",Rarity.Epic, mangoUrl, mangoSSUrl),
    SquidInk("Squid_Ink",Rarity.Epic, squidInkUrl, squidInkSSUrl),
    Pumpkinpie("Pumpkin_Pie",Rarity.Epic, pumpkinPieUrl, pumpkinPieSSUrl),
    Macaron("Macaron",Rarity.Epic, macaroonUrl, macaronSSUrl),
    Rye("Rye",Rarity.Epic, ryeUrl, ryeSSUrl),
    Tigerlily("Tiger_Lily",Rarity.Epic, tigerLilyUrl, tigerlilySSUrl),
    Pastry("Pastry",Rarity.Epic, pastryUrl, pastrySSUrl),
    Twizzlygummy("Twizzly_Gummy",Rarity.Epic, twizzlyGummyUrl, twizzlyGummySSUrl),
    Caramel("Caramel",Rarity.Epic, caramelUrl, caramelSSUrl),
    Chilipepper("Chili_Pepper",Rarity.Epic, chilipepperUrl, chilipepperSSUrl),
    Vampire("Vampire",Rarity.Epic, vampierUrl, vampireSSUrl),
    Blackraisin("Black_Raisin",Rarity.Epic, blackraisinUrl, blackraisinSSUrl),
    SorbetShark("Sorbet_Shark",Rarity.Epic, sorbetUrl, sorbetsharkSSUrl),
    Cherryblossom("Cherry_Blossom",Rarity.Epic, cherryBlossomUrl, cherryblossomSSUrl),
    Poisonmushroom("Poison_Mushroom",Rarity.Epic, poisonMushroomUrl, poisonmushroomSSUrl),
    Affogato("Affogato",Rarity.Epic, affogatoUrl, affogatoSSUrl),
    Captaincaviar("Captain_Caviar",Rarity.Epic, captainCavUrl, captaincavSSUrl),
    Pinecone("Pinecone",Rarity.Epic, pineconeUrl, pineconeSSUrl),
    Mintchoco("Mint_Choco",Rarity.Epic, mintchocoUrl, mintchocoSSUrl),
    Pomegranate("Pomegranate",Rarity.Epic, pomegranateUrl, pomegranateSSUrl),
    Almond("Almond",Rarity.Epic, almondUrl, almondSSUrl),
    Creampuff("Cream_Puff",Rarity.Epic, creampuffUrl, creampuffSSUrl),
    Fig("Fig",Rarity.Epic, figUrl, figSSUrl),
    Lilac("Lilac",Rarity.Epic, lilacUrl, lilacSSUrl),
    Parfait("Parfait",Rarity.Epic, parfaitUrl, parfaitSSUrl),
    Prophet("Prophet",Rarity.Epic, prophetUrl, prophetSSUrl),
    Cotton("Cotton",Rarity.Epic, cottonUrl, cottonSSUrl),
    Eclair("Eclair",Rarity.Epic, eclairUrl, eclairSSUrl),
    Candydiver("Candy_Diver",Rarity.Epic, candyDiverUrl, candydiverSSUrl),
    Herb("Herb",Rarity.Epic, herbUrl, herbSSUrl),
    Sparkling("Sparkling",Rarity.Epic, sparklingUrl, sparklingSSUrl),
    Creamunicorn("Cream_Unicorn",Rarity.Epic, creamUnicornUrl, creamunicornSSUrl),
    Carol("Carol",Rarity.Epic, carolUrl, carolSSUrl),
    //super epic
    Clottedcream("Clotted_Cream", Rarity.Special, clottedCreamUrl, clottedcreamSSUrl),
    Sherbet("Sherbet", Rarity.Special, sherbertUrl, sherbetSSUrl),
    Oyster("Oyster", Rarity.Special, oysterUrl, oysterSSUrl),
    //ancient
    PureVanilla("Pure_Vanilla", Rarity.Legendary, pureVanillaUrl, purevanillaSSUrl),
    Hollyberry("Hollyberry", Rarity.Legendary, hollyBerryUrl, hollyberrySSUrl),
    Darkcacao("Dark_Cacao", Rarity.Legendary, darkcacaoUrl, darkcacaoSSUrl),
    //Legendary
    SeaFairy("Sea_Fairy", Rarity.Legendary, seaFairyUrl, seafairySSUrl),
    Frostqueen("Frost_Queen", Rarity.Legendary, frostQueenUrl, frostqueenSSUrl),
    Blackpearl("Black_Pearl", Rarity.Legendary, blackPearlUrl, blackpearlSSUrl),
    Moonlight("Moonlight", Rarity.Legendary, moonlightUrl, moonlightSSUrl),
}

fun allCookies() = Cookie.values().toList()


fun List<Cookie>.filterRarity(rarity: Rarity) = this.filter {
    it.rarity == rarity
}.ifEmpty { listOf(Cookie.GingerBrave) }


fun getAllCookieNames() = Cookie.values().map { it.id }
