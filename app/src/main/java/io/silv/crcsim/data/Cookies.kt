package io.silv.crcsim.data

import io.silv.crcsim.models.Rarity

enum class Cookie(
    val id: String,
    val rarity: Rarity,
    val imageUrl: String,
    val soulstoneUrl: String,
    val gachaBgUrl: String,
) {
    //Common
    GingerBrave("GingerBrave", Rarity.Common, gingerBraveUrl, gingerbraveSSUrl, commonBGUrl),
    Muscle("Muscle", Rarity.Common, muscleUrl, muscleSSUrl, commonBGUrl),
    Strawberry("Strawberry", Rarity.Common, strawberryUrl, strawberrySSUrl, commonBGUrl),
    Wizard("Wizard", Rarity.Common, wizardUrl, wizardSSUrl, commonBGUrl),
    Beet("Beet", Rarity.Common, beetUrl, beetSSUrl, commonBGUrl),
    Ninja("Ninja",Rarity.Common, ninjaUrl, ninjaSSUrl, commonBGUrl),
    Angel("Angel",Rarity.Common, angelUrl, angelSSUrl, commonBGUrl),
    //Rare,_root_ide_package_.io.silv.crcsim.data.rareBGURl
    Princess("Princess",Rarity.Rare, princessUrl, princessSSUrl, rareBGURl),
    Avocado("Avocado",Rarity.Rare, avocadoUrl, avocadoSSUrl, rareBGURl),
    Knight("Knight",Rarity.Rare, knightUrl, knightSSUrl, rareBGURl),
    Blackberry("Blackberry",Rarity.Rare, blackberryUrl,  blackberrySSUrl, rareBGURl),
    Devil("Devil",Rarity.Rare, devilUrl, devilSSUrl, rareBGURl),
    Adventurer("Adventurer",Rarity.Rare, adventurerUrl, adventurerSSUrl, rareBGURl),
    Pancake("Pancake",Rarity.Rare, pancakeUrl, pancakeSSUrl, rareBGURl),
    Alchemist("Alchemist",Rarity.Rare, alchemistUrl, alchemistSSUrl, rareBGURl),
    Cherry("Cherry",Rarity.Rare, cherryUrl, cherrySSUrl, rareBGURl),
    Gumball("Gumball",Rarity.Rare, gumballUrl, gumballSSUrl, rareBGURl),
    Carrot("Carrot",Rarity.Rare, carrotUrl, carrotSSUrl, rareBGURl),
    Onion("Onion",Rarity.Rare, onionUrl, onionSSUrl, rareBGURl),
    Clover("Clover",Rarity.Rare, cloverurl, cloverSSUrl, rareBGURl),
    Custard("Custard",Rarity.Rare, custardUrl, custardSSUrl, rareBGURl),
    //Special
    Sonic("Sonic",Rarity.Special, sonicUrl, sonicSSUrl, sonicUrlBG),
    Tails("Tails",Rarity.Special, tailsUrl, tailsSSUrl, tailsUrlBG),
    RM("RM",Rarity.Special, rmUrl, rmSSUrl, rmUrlBG),
    Jin("Jin",Rarity.Special, jinUrl, jinSSUrl, jinUrlBG),
    Suga("Suga",Rarity.Special, sugaUrl, sugaSSUrl, sugaUrlBG),
    Jhope("J_Hope",Rarity.Special, jHopeUrl, jHopeSSUrl, jHopeUrlBG),
    Jimin("Jimin",Rarity.Special, jiminUrl, jiminSSUrl, jiminUrlBG),
    V("V",Rarity.Special, vUrl, vSSUrl, vUrlBG),
    Jungkook("Jung_Kook",Rarity.Special, jungKookUrl, jungkookSSUrl, jungKookUrlBG),
    //Epic
    Darkchoco("Dark_Choco",Rarity.Epic, darkChocoUrl, darkChocoSSUrl, darkChocoUrlBG),
    Yam("Purple_Yam",Rarity.Epic, yamUrl, yamSSUrl, yamUrlBG),
    Werewolf("Werewolf",Rarity.Epic, werewolfUrl, werewolfSSUrl, werewolfUrlBG),
    Kumiho("Kumiho",Rarity.Epic, kumihoUrl, kumihoSSUrl, kumihoUrlBG),
    Redvelvet("Red_Velvet",Rarity.Epic, redVelvetUrl, redvelvetSSUrl, redVelvetUrlBG),
    Raspberry("Raspberry",Rarity.Epic, raspberryUrl, raspberrySSUrl, raspberryUrlBG),
    Malasauce("Mala_Sauce",Rarity.Epic, malaSauceUrl, malasauceSSUrl, malaSauceUrlBG),
    Teaknight("Tea_Knight",Rarity.Epic, teaKnightUrl, teaknightSSUrl, teaKnightUrlBG),
    Crunchychip("Crunchy_Chip",Rarity.Epic, crunchyChipUrl, crunchychipSSUrl, crunchyChipUrlBG),
    Schwarzwalder("Schwarzwalder",Rarity.Epic, schwarzwalderUrl, schwarzwalderSSUrl, schwarzwalderUrlBG),
    MilkyWay("Milky_Way",Rarity.Epic, milkyWayUrl, milkywaySSUrl, milkyWayUrlBG),
    Made("Made",Rarity.Epic, madeUrl, madeSSUrl, madeUrlBG),
    Milk("Milk",Rarity.Epic, milkUrl, milkSSUrl, milkUrlBG),
    Strawberrycrepe("Strawberry_Crepe",Rarity.Epic, strawberryCrepeUrl, strawberrycrepeSSUrl, strawberryCrepeUrlBG),
    MoonRabbit("Moon_Rabbit",Rarity.Epic, moonRabbitUrl, moonRabbitSSurl, moonRabbitUrlBG),
    Cocoa("Cocoa",Rarity.Epic, cocoaUrl, cocoaSSUrl, cocoaUrlBG),
    Wildberry("Wildberry",Rarity.Epic, wildberryUrl, wildberrySSUrl, wildberryUrlBG),
    Financier("Financier",Rarity.Epic, financierUrl, financierSSUrl, financierUrlBG),
    Lico("Lico",Rarity.Epic, licoUrl, licoSSUrl, licoUrlBG),
    Snowsugar("Snow_Sugar",Rarity.Epic, snowsugarUrl, snowsugarSSUrl, snowsugarUrlBG),
    Espresso("Espresso",Rarity.Epic, espressoUrl, espressoSSUrl, espressoUrlBG),
    Latte("Latte",Rarity.Epic, latteUrl, latteSSUrl, latteUrlBG),
    Mango("Mango",Rarity.Epic, mangoUrl, mangoSSUrl, mangoUrlBG),
    SquidInk("Squid_Ink",Rarity.Epic, squidInkUrl, squidInkSSUrl, squidInkUrlBG),
    Pumpkinpie("Pumpkin_Pie",Rarity.Epic, pumpkinPieUrl, pumpkinPieSSUrl, pumpkinPieUrlBG),
    Macaron("Macaron",Rarity.Epic, macaroonUrl, macaronSSUrl, macaroonUrlBG),
    Rye("Rye",Rarity.Epic, ryeUrl, ryeSSUrl, ryeUrlBG),
    Tigerlily("Tiger_Lily",Rarity.Epic, tigerLilyUrl, tigerlilySSUrl, tigerLilyUrlBG),
    Pastry("Pastry",Rarity.Epic, pastryUrl, pastrySSUrl, pastryUrlBG),
    Twizzlygummy("Twizzly_Gummy",Rarity.Epic, twizzlyGummyUrl, twizzlyGummySSUrl, twizzlyGummyUrlBG),
    Caramel("Caramel",Rarity.Epic, caramelUrl, caramelSSUrl, caramelUrlBG),
    Chilipepper("Chili_Pepper",Rarity.Epic, chilipepperUrl, chilipepperSSUrl, chilipepperUrlBG),
    Vampire("Vampire",Rarity.Epic, vampierUrl, vampireSSUrl, vampierUrlBG),
    Blackraisin("Black_Raisin",Rarity.Epic, blackraisinUrl, blackraisinSSUrl, blackraisinUrlBG),
    SorbetShark("Sorbet_Shark",Rarity.Epic, sorbetUrl, sorbetsharkSSUrl, sorbetUrlBG),
    Cherryblossom("Cherry_Blossom",Rarity.Epic, cherryBlossomUrl, cherryblossomSSUrl, cherryBlossomUrlBG),
    Poisonmushroom("Poison_Mushroom",Rarity.Epic, poisonMushroomUrl, poisonmushroomSSUrl, poisonMushroomUrlBG),
    Affogato("Affogato",Rarity.Epic, affogatoUrl, affogatoSSUrl, affogatoUrlBG),
    Captaincaviar("Captain_Caviar",Rarity.Epic, captainCavUrl, captaincavSSUrl, captainCavUrlBG),
    Pinecone("Pinecone",Rarity.Epic, pineconeUrl, pineconeSSUrl, pineconeUrlBG),
    Mintchoco("Mint_Choco",Rarity.Epic, mintchocoUrl, mintchocoSSUrl, mintchocoUrlBG),
    Pomegranate("Pomegranate",Rarity.Epic, pomegranateUrl, pomegranateSSUrl, pomegranateUrlBG),
    Almond("Almond",Rarity.Epic, almondUrl, almondSSUrl, almondUrlBG),
    Creampuff("Cream_Puff",Rarity.Epic, creampuffUrl, creampuffSSUrl, creampuffUrlBG),
    Fig("Fig",Rarity.Epic, figUrl, figSSUrl, figUrlBG),
    Lilac("Lilac",Rarity.Epic, lilacUrl, lilacSSUrl, lilacUrlBG),
    Parfait("Parfait",Rarity.Epic, parfaitUrl, parfaitSSUrl, parfaitUrlBG),
    Prophet("Prophet",Rarity.Epic, prophetUrl, prophetSSUrl, prophetUrlBG),
    Cotton("Cotton",Rarity.Epic, cottonUrl, cottonSSUrl, cottonUrlBG),
    Eclair("Eclair",Rarity.Epic, eclairUrl, eclairSSUrl, eclairUrlBG),
    Candydiver("Candy_Diver",Rarity.Epic, candyDiverUrl, candydiverSSUrl, candyDiverUrlBG),
    Herb("Herb",Rarity.Epic, herbUrl, herbSSUrl, herbUrlBG),
    Sparkling("Sparkling",Rarity.Epic, sparklingUrl, sparklingSSUrl, sparklingUrlBG),
    Creamunicorn("Cream_Unicorn",Rarity.Epic, creamUnicornUrl, creamunicornSSUrl, creamUnicornUrlBG),
    Carol("Carol",Rarity.Epic, carolUrl, carolSSUrl, carolUrlBG),
    //super epic
    Clottedcream("Clotted_Cream", Rarity.Special, clottedCreamUrl, clottedcreamSSUrl, clottedCreamUrlBG),
    Sherbet("Sherbet", Rarity.Special, sherbertUrl, sherbetSSUrl, sherbertUrlBG),
    Oyster("Oyster", Rarity.Special, oysterUrl, oysterSSUrl, oysterUrlBG),
    //ancient
    PureVanilla("Pure_Vanilla", Rarity.Legendary, pureVanillaUrl, purevanillaSSUrl, pureVanillaUrlBG),
    Hollyberry("Hollyberry", Rarity.Legendary, hollyBerryUrl, hollyberrySSUrl, hollyBerryUrlBG),
    Darkcacao("Dark_Cacao", Rarity.Legendary, darkcacaoUrl, darkcacaoSSUrl, darkcacaoUrlBG),
    //Legendary
    SeaFairy("Sea_Fairy", Rarity.Legendary, seaFairyUrl, seafairySSUrl, seaFairyUrlBG),
    Frostqueen("Frost_Queen", Rarity.Legendary, frostQueenUrl, frostqueenSSUrl, frostQueenUrlBG),
    Blackpearl("Black_Pearl", Rarity.Legendary, blackPearlUrl, blackpearlSSUrl, blackPearlUrlBG),
    Moonlight("Moonlight", Rarity.Legendary, moonlightUrl, moonlightSSUrl, moonlightUrlBG),
}

fun allCookies() = Cookie.values().toList()


fun List<Cookie>.filterRarity(rarity: Rarity) = this.filter {
    it.rarity == rarity
}.ifEmpty { listOf(Cookie.GingerBrave) }


fun getAllCookieNames() = Cookie.values().map { it.id }
