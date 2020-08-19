Feature: Functional Tests for SWAPI

  Background: 
    Given the endpoint is up

  @smoketest
  Scenario Outline: Get list of all star wars characters
    When I send GET request for "/api/people/" with query parameter <pagenumber>
    Then the status code is 200
    And total number of characters equal to 82
    And it retrieves the list of star wars <characterNames>

    Examples: 
      | pagenumber | characterNames                                                                                                                               |
      | "1"        | "Luke Skywalker,C-3PO,R2-D2,Darth Vader,Leia Organa,Owen Lars,Beru Whitesun lars,R5-D4,Biggs Darklighter,Obi-Wan Kenobi"                     |
      | "2"        | "Anakin Skywalker, Wilhuff Tarkin, Chewbacca, Han Solo, Greedo, Jabba Desilijic Tiure, Wedge Antilles, Jek Tono Porkins, Yoda, Palpatine"    |
      | "3"        | "Boba Fett, IG-88, Bossk, Lando Calrissian, Lobot, Ackbar, Mon Mothma, Arvel Crynyd, Wicket Systri Warrick, Nien Nunb"                       |
      | "4"        | "Qui-Gon Jinn, Nute Gunray, Finis Valorum, Padmé Amidala, Jar Jar Binks, Roos Tarpals, Rugor Nass, Ric Olié, Watto, Sebulba"                 |
      | "5"        | "Quarsh Panaka, Shmi Skywalker, Darth Maul, Bib Fortuna, Ayla Secura, Ratts Tyerel, Dud Bolt, Gasgano, Ben Quadinaros, Mace Windu"           |
      | "6"        | "Ki-Adi-Mundi, Kit Fisto, Eeth Koth, Adi Gallia, Saesee Tiin, Yarael Poof, Plo Koon, Mas Amedda, Gregar Typho, Cordé"                        |
      | "7"        | "Cliegg Lars, Poggle the Lesser, Luminara Unduli, Barriss Offee, Dormé, Dooku, Bail Prestor Organa, Jango Fett, Zam Wesell, Dexter Jettster" |
      | "8"        | "Lama Su, Taun We, Jocasta Nu, R4-P17, Wat Tambor, San Hill, Shaak Ti, Grievous, Tarfful, Raymus Antilles"                                   |
      | "9"        | "Sly Moore, Tion Medon"                                                                                                                      |

  @smoketest
  Scenario Outline: Get list of all star wars planets
    When I send GET request for "/api/planets/" with query parameter <pagenumber>
    Then the status code is 200
    And total number of planets equal to 60
    And it retrieves the list of star wars planet <names>

    Examples: 
      | pagenumber | names                                                                                                     |
      | "1"        | "Tatooine, Alderaan, Yavin IV, Hoth, Dagobah, Bespin, Endor, Naboo, Coruscant, Kamino"                    |
      | "2"        | "Geonosis, Utapau, Mustafar, Kashyyyk, Polis Massa, Mygeeto, Felucia, Cato Neimoidia, Saleucami, Stewjon" |
      | "3"        | "Eriadu, Corellia, Rodia, Nal Hutta, Dantooine, Bestine IV, Ord Mantell, unknown, Trandosha, Socorro"     |
      | "4"        | "Mon Cala, Chandrila, Sullust, Toydaria, Malastare, Dathomir, Ryloth, Aleen Minor, Vulpter, Troiken"      |
      | "5"        | "Tund, Haruun Kal, Cerea, Glee Anselm, Iridonia, Tholoth, Iktotch, Quermia, Dorin, Champala"              |
      | "6"        | "Mirial, Serenno, Concord Dawn, Zolan, Ojom, Skako, Muunilinst, Shili, Kalee, Umbara"                     |

  Scenario: Get all details of specified star wars character
    When I send GET request for "/api/people/1"
    Then the status code is 200
    And it retrieves all the details of the specified character

  Scenario: Get all details of specified star wars planet
    When I send GET request for "/api/planets/1"
    Then the status code is 200
    And it retrieves all the details of the specified planet

  Scenario: Search for a specific character
    When I send GET request for "api/people" with query parameter search= "Darth Vader"
    Then the status code is 200
    And it retrieves correct details of that character "Darth Vader"

  Scenario: Search for a specific planet
    When I send GET request for "api/planets" with query parameter search= "Chandrila"
    Then the status code is 200
    And it retrieves correct details of that planet "Chandrila"
