# Plan-a-Meal: White Paper

*Skriven av Johan Romeo*

# Innehållsförteckning

---

- [Inledning. . . . .](#inledning)
- [Projektöversikt. . . . .](#projektöversikt)
    - [Livsmedel - en översikt. . . . .](#livsmedel---en-översikt)
        - [Funktioner för livsmedel. . . . .](#funktioner-för-livsmedel)
    - [Inköpslista - en översikt. . . . .](#inköpslista---en-översikt)
        - [Funktioner för inköpslista. . . . .](#funktioner-för-inköpslista)
- [Receptförslag - en översikt. . . . .](#receptförslag---en-översikt)
- [Förväntade resultat och måluppfyllelse. . . . .](#förväntade-resultat-och-måluppfyllelse)
- [Milstolpar och delmål. . . . .](#milstolpar-och-delmål)
    - [Frontend. . . . .](#frontend)
    - [Backend. . . . .](#backend)
- [Metodik och tillvägagångssätt. . . . .](#metodik-och-tillvägagångssätt)
    - [Arbetsmetodik. . . . .](#arbetsmetodik)
    - [Hjälpredskap. . . . .](#hjälpredskap)
- [Riskhantering och utmaningar. . . . .](#riskhantering-och-utmaningar)
- [Tidsplan och resurser. . . . .](#tidpslan-och-resurser)
    - [Tid - ej ett faktum. . . . .](#tid---ej-ett-faktum)
    - [Resurser - länkar till material. . . . .](#resurser---länkar-till-material)
        - [Frontend. . . . .](#frontend-1)
        - [Backend. . . . .](#backend-1)
- [Avslutande Reflektioner. . . . .](#avslutande-reflektioner)
    - [Kompetenser och kunskaper jag förväntas få. . . . .](#kompetenser-och-kunskaper-jag-förväntas-få)

---

# Inledning
Att planera måltider och skriva inköpslistor är inte alltid så enkelt som det kan verka.
<br>Ibland glömmer man bort det eller saknar tid. Det händer även att man av ren bekvämlighet eller brist på motivation
<br>undviker att göra inköpslistor, vilket ofta leder till att man köper mer livsmedel än vad som egentligen behövs.
<br>Vanligtvis görs inköpen utifrån vad man planerar att laga för mat, men vad händer med de livsmedel som blir över
<br>och inte används? Ska man laga samma rätt igen, eller kan man vara mer kreativ i köket?
När inspirationen och energin sinar kan det vara till hjälp att få stöd.
<br><br>**Plan-a-Meal** finns för att förenkla detta genom att erbjuda AI-baserade receptförslag baserat på de livsmedel du redan har hemma.

---

# Projektöversikt
Webbapplikationen kommer tillhandahålla användaren ett enkelt användargränssnitt som möjliggör en rad funktioner för att
<br>ha inventarier av hushållets livsmedel, samt att generera inköpslistor och AI-baserade receptförslag.

## Livsmedel - en översikt
Tack vare Livsmedelsverkets databas med över 2600 tillgängliga livsmedel, kan användaren lägga till önskade livsmedel i hushållets inventarier.
<br>Varje enskilt livsmedel anges som enhet och inte ett specifikt mått av ett livsmedel.
<br>Detta är nödvändigt för att på ett så användarvänligt sätt som möjligt, kunna generera inköpslistor samt att AI-generera receptförslag.

**Nedan exempel demonstrerar ett scenario:**
Johan köper 2 st mjölkpaket à 2 liter vardera.
<br>Han vill lägga till dem till hushållets inventarier och skriver då in “Mjölk” i användargränssnittet och anger därefter ett antal enheter av “Mjölk”, alltså 2 st enheter.
<br>Skulle enhetsmått användas istället, hade det medfört en rad ytterligare uppgifter för Johan, som att till exempel ange
<br>hur många liter mjölk han har köpt och senare uppdatera hushållets inventarier med hur många liter eller deciliter det finns kvar.
<br>Att göra detta för varje livsmedel är för tidskrävande.
Webbapplikationen har som mål att göra det så enkelt för användaren som möjligt, men kräver dock att vissa moment utförs av användaren.
<br>Detta kommer att beskrivas under rubriken: “Inköpslista - en översikt”.

### Funktioner för livsmedel:
* Söka i Livsmedelsverket databas och lägga till ett livsmedel i inventariet.
* Lista upp tillgängliga livsmedel i inventariet.
* Uppdatera antalet av ett specifikt livsmedel i inventariet.
* Ta bort ett livsmedel från inventariet.
* Stjärnmarkera ofta inhandlade livsmedel.

### Inköpslista - en översikt
En inköpslista skapas baserat på de livsmedel som användaren saknar.
<br>I ovan nämnda exempel fick vi veta att Johan har lagt till 2 st “Mjölk”. Men hur vet webbapplikationen att den ska skapa en inköpslista när mjölken tar slut?

I användargränssnittet syns en bild på ett mjölkpaket. Under bilden kommer antalet av “Mjölk”, samt en “+” och en “-”-knapp, att visas.
<br>Knapparna är till för att addera eller subtrahera antalet mjölkpaket från inventariet.
<br>När antalet är 0, kommer användaren få förfrågan om mjölk ska läggas till i inköpslistan.
<br>Är svaret ja, anges ett antal och läggs sedan till i inköpslistan. Om svaret är nej händer ingenting och användaren
<br>kan vid ett senare tillfälle lägga till livsmedlet till inköpslistan.

### Funktioner för inköpslista:
* Fråga användare om livsmedel med “0” i antal enheter skall läggas till i inköpslistan.
* Låta användare bestämma när inköpslistan är redo för att fulländas.
* Exportera fulländad inköpslista som .pdf.

### Receptförslag - en översikt
Baserat på de livsmedel som finns i hushållets inventarier, kan användaren få AI-genererade receptförslag baserat på användarens önskemål.
Då dessa kan variera kraftigt, kommer webapplikationen utvecklas på ett sätt som tillgodoser olika typer av önskemål, men med ett begränsat antal och med de “viktigaste” i åtanke:

* Om receptet ska vara; frukost, middag, kvällsmat, etc.
* Vad det ska vara för typ av mat; vegetariskt eller kött.
* Maximalt antal minuter för utförande av recept.
* Om det ska vara något specifikt tema; Italienskt, Svenskt, Indiskt, etc.
* Lägga till recept som favorit.

---

# Förväntade resultat och måluppfyllelse

Som utvecklare av projektet förväntar mig en välfungerande webbapplikation som är responsiv och enkel att använda, där varje
<br>funktion bringar en mening och ger användaren en känsla av lättsamhet av tanken att inte behöva göra mer än det som krävs.

## Milstolpar och delmål

### Frontend
**HTML:**
* Förvärva kunskap i hur man strukturerar upp rubriker, underrubriker och brödtext.
* Strukturera upp data på ett användarvänligt sätt.

**CSS:**
* Förvärva kunskap om val av färger och hur dessa används för att skapa en visuellt tilltalande webbapplikation.
* Bygga en responsiv webbapplikation.

**JavaScript:**
* Förvärva kunskap om vilka funktioner som behövs och hur de skall användas i webbapplikationen.
* Kunna utföra HTTP anrop och hantera inkommande data.

### Backend
**Java**
* Uppsättning samt hantering av APIer, data och logik som möjliggör integration med Frontend.

**MySQL**
* Relationer mellan entiteter

**Raspberry Pi 5**
* Server för Docker Containers och få dessa att kommunicera med varandra.

**Docker**
* Containerisera flera applikationer.
* Användande Docker Compose för containerisering av flera Docker Containers.
* Skydda känslig information som API-nycklar i configurations mappar.

---

# Metodik och tillvägagångssätt

## Arbetsmetodik

### 
1) **Läsa & Förstå**
    - Kunskap fås genom att läsa samlat material för att klara varje delmål.

2) **Kreativitet**
    - Utforska på egen hand det jag lärt mig. Jag provar, gör fel och gör sedan rätt.

3) **Implementera**
    - När steg 2 har utförts, skrivs sedan koden för att varje delmål skall avklaras.

4) **Optimera**
    - Implementerad kod optimeras så att förväntade resultat och användarvänlighet är fulländad.

**I följande ordning kommer webbapplikationen utvecklas:**

Backend (MVP) → Containerisera applikationer med Docker → Frontend (MVP) → Containerisera applikation med Docker

### Hjälpredskap
* Postman för testning av API endpoints.
* Git och GitHub för versionshantering av kod.
* ChatGPT för bollplank och förklaring vid behov.
* UML diagram för visualisering och planering av backend.
* Obsidian för anteckningar och dagbok.

---

# Riskhantering och utmaningar
Förberedelser innan påbörjandet av samtliga delmoment är avgörande för ett lyckat projekt.
<br>För att minska chansen att fastna på ett delmål så skall den officiella dokumentationen av berörande teknik läsas utförligt innan kod börjar skrivas.

Till en början är det viktigaste att webbapplikationen gör det den är menad att göra.
<br>Därför har jag som mål att utveckla en MVP (Minimum Viable Product) och förbättra den gradvis.

Trots detta, finns det en rad utmaningar jag ser komma under projektets gång. De viktigaste listas nedan:

* Lyckas generera bild till varje enskilt livsmedel i hushållets inventarier och lista dessa på ett visuellt tilltalande sätt.
* Bygga en responsiv webbapplikation.
* Få korrekta och pålitliga AI-genererade recept.
* Få samtliga containeriserade applikationer att kommunicera.
* Tillhandahålla Docker Containers med API-nycklar på ett säkert sätt.

---

# Tidpslan och resurser

## Tid - ej ett faktum
Detta är ett projekt menat att arbeta på vid sidan av heltidsstudier. Målet är dock att avsätta tid varje kväll.
<br>Projektet är menat att skapas med glädje, intresse och entusiasm och jag fruktar för mindre positiva känslor om ett satt tidschema sätts och ej kan hållas.

## Resurser - länkar till material

### Frontend

**HTML**
* [HTML Basics](https://www.udemy.com/course/design-and-develop-a-killer-website-with-html5-and-css3/learn/lecture/27511920#reviews)

**CSS**
* [CSS Basics](https://www.udemy.com/course/design-and-develop-a-killer-website-with-html5-and-css3/learn/lecture/27511920#reviews)

**JavaScript**
* [JavaScript Fundamentals](https://www.udemy.com/course/javascriptfundamentals/learn/lecture/6213836#overview)
* [JavaScript Advanced](https://www.udemy.com/course/advanced-and-object-oriented-javascript/learn/lecture/10029464#overview)

### Backend

**Java**
* [Getting Started with GPT-4o in Java](https://www.youtube.com/watch?v=EDJLHWcFvpQ&t=651s&ab_channel=DanVega)
* [Environment variables](https://www.youtube.com/watch?v=jNOh4jQJG2U&ab_channel=SamieAzubike)
* [@Asynch in Spring Boot](https://spring.io/guides/gs/async-method)
* [Apache POI - .docx in Java](https://www.geeksforgeeks.org/java-apache-poi-programs/)
* [Send email with SpringBoot](https://www.youtube.com/watch?v=ThOdWjAz5cw&ab_channel=CodeDecode)
* [Qustom SQL queries - Spring Boot](https://www.udemy.com/course/building-real-time-rest-apis-with-spring-boot/learn/lecture/31749904#overview)
* [Pagination and sorting](https://www.udemy.com/course/building-real-time-rest-apis-with-spring-boot/learn/lecture/28678120#overview)
* [Validation in Spring Boot](https://www.udemy.com/course/building-real-time-rest-apis-with-spring-boot/learn/lecture/28679366#overview)

**OpenAI**
* [OpenAI API](https://platform.openai.com/docs/overview)

**Raspberry Pi**
* [Install Docker Engine on Debian](https://docs.docker.com/engine/install/debian/)

**Docker**
* [Docker in Spring Boot](https://www.udemy.com/course/building-real-time-rest-apis-with-spring-boot/learn/lecture/35754792#overview)
* [Docker Compose - JavaGuides](https://www.udemy.com/course/building-real-time-rest-apis-with-spring-boot/learn/lecture/35754898#overview)
* [Docker Compose - Ali](https://www.youtube.com/watch?v=LNL0h66FXu0&ab_channel=BoualiAli)
* [Docker Secrets](https://docs.docker.com/compose/how-tos/use-secrets/)
* [Docker Hub](https://docs.docker.com/docker-hub/)
* [CLI Cheat Sheet](https://docs.docker.com/get-started/docker_cheatsheet.pdf)


# Avslutande reflektioner

## Kompetenser och kunskaper jag förväntas få
Genom detta projekt förväntar jag mig att förvärva kunskap och kompetens inom följande fokusområden samt…

* …kunna utveckla webbsidor och webbapplikationer med HTML, CSS och JavaScript med god potential att utforska ramverk som React.

* …kunna använda mig av Docker för att containerisera applikationer samt etablera kommunikation mellan dessa med Docker Compose.

* …kunna ytterligare utforska generativ AI samt integrera det i webbapplikationer.

* …kunna planera upp arbete på ett sätt som underlättar mitt lärande och projektutveckling.

* …få utmana mig själv genom användandet av delvis- och helt nya tekniker.

Genom att förvärva dessa kompetenser och kunskaper är jag övertygad att jag kommer att känna en stolthet att underlätta
<br>samt effektivisera livet för min familj och andra användare genom utvecklandet av denna webbapplikation.
<br>Dessutom tror jag att det kommer gynna min framtida karriär, då jag har ett projekt som använder sig av populära tekniker.

---





