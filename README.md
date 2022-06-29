# VIZSGAREMEK

## ***Project leírása***

A vizsgaremekhez a mentorom, kifejezetten tesztelési célra létrehozott weboldalát választottam.

A tesztjeim célja az oldal használhatóságának növelése.

[Ezen a linken](https://lennertamas.github.io/portio/landing.html) megtekinthető a tesztelt oldal.

A vizsgaremekem a [Github profilom](https://github.com/Rottohu/Vizsgaremek) alatt található meg.

A vizsgaremek repositoryja tartalmazza az [excel fájlban](https://github.com/Rottohu/Vizsgaremek/blob/main/Test%20Cases.xlsx) elkészített teszteseteket és a tesztek sikeres futásához szükséges fájlokat.

Az automata tesztek megvalósításához **_Java_** nyelvet és **_Selenium Webdrivert_**  használtam.

A riportolást az **_Allure Framework_** végzi, amely minden egyes commit alkalmával újra generálja a riportot, az előzőeket is megtartva.

A riportjaim a tesztek eredményéről [itt](https://rottohu.github.io/Vizsgaremek) tekinthetőek meg.

Az automata tesztek során az alábbi funkciókat teszteltem:

- Regisztráció
- Bejelentkezés
- Adatkezelési nyilatkozat használata
- Adatok listázása
- Több oldalas lista bejárása
- Új adat bevitel
- Ismételt és sorozatos adatbevitel adatforrásból
- Meglévő adat módosítás
- Adat vagy adatok törlése
- Adatok lementése felületről
- Kijelentkezés

Minden tesztemet egy **rövid névvel vagy leírással** és **csoportosítási annotációval** láttam el, az átláthatóság miatt:

```java
@Tag("DataSaving")
@Description("Download all image to a newly created folder from the landing page")
@Test
```

