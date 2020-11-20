package com;

import com.msgboard.Attachment;
import com.msgboard.Msg;
import com.msgboard.MsgBoard;
import com.msgboard.UserAuth;
import com.persistence.Attachments;
import com.persistence.UserData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class Test {

  public static void main(String[] args) throws Exception {
    // Creating a messageBoard object
    MsgBoard msgBoard = new MsgBoard();

    // Creating Dummy user and testing the authentication status
    for (UserAuth userAuth : new UserAuth[] {
      new UserAuth("antoine"),
      new UserAuth("zayn"),
      new UserAuth("alex"),
      new UserAuth("wen"),
      new UserAuth("james")
    }) {
      String authStatus = userAuth.authenticate(userAuth.username);
      System.out.println(userAuth.username + ": " + authStatus);
    }

    // Creates dummy messages
    //            for (var i = 0; i < 20; i++) {
    //                        Msg msg = new Msg(
    //                          i < 10 ? "antoine" : "jeremy",
    //                          String.format(
    //                            "I live in #%s. I love the #weather here. Coronavirus is nice. #People are nice. #%s is great.",
    //                            Cities.cities[i],
    //                            Cities.cities[Cities.cities.length - 1 - i]
    //                          ), null
    //                        );
    //                        msgBoard.postMsg(msg);
    //                }

    Attachment attachment = new Attachment("ex.png", "png", new File("ex.png"));
    System.out.println(attachment);
    msgBoard.postMsg(
      new Msg("jeeee", "Hello, I am #Antoine, I go to sleep #notlate", attachment)
    );

    Attachments attachments = new Attachments();
    attachments.get("1");
    //    var Attach = attachments.get("1");

    // [TESTING]
    // Persistence - UserData
    //    UserData data = new UserData();
    //    File userData = data.userDataToJson("antoine");
    //    System.out.println("\n" + userData.toString() + "\n");
    // MsgBoard.getMsg();
    //    List<List<String>> found = msgBoard.getMsg();
    //    int count = found.size();
    //    System.out.printf("\nMsgBoard.getMsg();");
    //    System.out.printf("\nCOUNT: %s, FOUND: %s%n", count, found.size());
    //    System.out.printf(Arrays.toString(found.toArray()));

    //
    //    // MsgBoard.getMsg(String username, String fromDateTime, String toDateTime, String[] hashtags);
    //    List<List<String>> found2 = msgBoard.getMsg(
    //      "antoine",
    //      "2020-04-01 22:56:01",
    //      "2020-12-01 22:56:01",
    //      new String[] { "Akron", "Albany", "Albuquerque", "Alexandria" }
    //    );
    //    List<List<String>> found3 = msgBoard.getMsg(
    //      "jeremy",
    //      "2020-11-19 22:56:01",
    //      "2020-12-01 22:56:01",
    //      new String[] {}
    //    );
    //    List<List<String>> found4 = msgBoard.getMsg(
    //      "jeremy",
    //      "2020-01-19 22:56:01",
    //      "2020-12-01 22:56:01",
    //      new String[] { "Appleton" }
    //    );
    //    System.out.printf(
    //      "\nMsgBoard.getMsg(String username, String fromDateTime, String toDateTime, String[] hashtags);"
    //    );
    //    System.out.printf("\nCOUNT: %s, FOUND2: %s%n", count, found2.size());
    //    System.out.printf("\nCOUNT: %s, FOUND3: %s%n", count, found3.size());
    //    System.out.printf("\nCOUNT: %s, FOUND4: %s%n", count, found4.size());
    //
    //    // MsgBoard.getMsg(String numberOfResults);
    //    List<List<String>> found5 = msgBoard.getMsg("10");
    //    System.out.printf("\nMsgBoard.getMsg(String numberOfResults);");
    //    System.out.printf("\nCOUNT: %s, FOUND5: %s%n", count, found5.size());
    //
    //    // MsgBoard.delteMsg(String messageid, String username);
    //    msgBoard.deleteMsg("3", "antoine");
    //    List<List<String>> found6 = msgBoard.getMsg(
    //      "antoine",
    //      "2020-04-01 22:56:01",
    //      "2020-12-01 22:56:01",
    //      new String[] {}
    //    );
    //    System.out.printf("\nMsgBoard.delteMsg(String messageid, String username);");
    //    System.out.printf("\nCOUNT: %s, FOUND5: %s%n", count, found6.size());
    //
    //    // Messages before edit;
    //    // Printing all the messages
    //    found.forEach(System.out::println);
    //
    //    // MsgBoard.updateMsg(String messageid, Msg msg);
    //    msgBoard.updateMsg(
    //      "1",
    //      new Msg("antoine", "Hello, I am #Antoine, I go to sleep #late", null)
    //    );
    //    List<List<String>> found7 = msgBoard.getMsg(
    //      "antoine",
    //      "2020-04-01 22:56:01",
    //      "2020-12-01 22:56:01",
    //      new String[] {}
    //    );
    //    System.out.printf("\nMsgBoard.updateMsg(String messageid, Msg msg);");
    //    System.out.printf("\nCOUNT: %s, FOUND7: %s%n", count, found7.size());
    //
    //    // Printing all the messages
    //    found.forEach(System.out::println);
  }

  // List of citites to simulate messages
  public static class Cities {
    static final String[] cities = new String[] {
      "Aberdeen",
      "Abilene",
      "Akron",
      "Albany",
      "Albuquerque",
      "Alexandria",
      "Allentown",
      "Amarillo",
      "Anaheim",
      "Anchorage",
      "Ann Arbor",
      "Antioch",
      "Apple Valley",
      "Appleton",
      "Arlington",
      "Arvada",
      "Asheville",
      "Athens",
      "Atlanta",
      "Atlantic City",
      "Augusta",
      "Aurora",
      "Austin",
      "Bakersfield",
      "Baltimore",
      "Barnstable",
      "Baton Rouge",
      "Beaumont",
      "Bel Air",
      "Bellevue",
      "Berkeley",
      "Bethlehem",
      "Billings",
      "Birmingham",
      "Bloomington",
      "Boise",
      "Boise City",
      "Bonita Springs",
      "Boston",
      "Boulder",
      "Bradenton",
      "Bremerton",
      "Bridgeport",
      "Brighton",
      "Brownsville",
      "Bryan",
      "Buffalo",
      "Burbank",
      "Burlington",
      "Cambridge",
      "Canton",
      "Cape Coral",
      "Carrollton",
      "Cary",
      "Cathedral City",
      "Cedar Rapids",
      "Champaign",
      "Chandler",
      "Charleston",
      "Charlotte",
      "Chattanooga",
      "Chesapeake",
      "Chicago",
      "Chula Vista",
      "Cincinnati",
      "Clarke County",
      "Clarksville",
      "Clearwater",
      "Cleveland",
      "College Station",
      "Colorado Springs",
      "Columbia",
      "Columbus",
      "Concord",
      "Coral Springs",
      "Corona",
      "Corpus Christi",
      "Costa Mesa",
      "Dallas",
      "Daly City",
      "Danbury",
      "Davenport",
      "Davidson County",
      "Dayton",
      "Daytona Beach",
      "Deltona",
      "Denton",
      "Denver",
      "Des Moines",
      "Detroit",
      "Downey",
      "Duluth",
      "Durham",
      "El Monte",
      "El Paso",
      "Elizabeth",
      "Elk Grove",
      "Elkhart",
      "Erie",
      "Escondido",
      "Eugene",
      "Evansville",
      "Fairfield",
      "Fargo",
      "Fayetteville",
      "Fitchburg",
      "Flint",
      "Fontana",
      "Fort Collins",
      "Fort Lauderdale",
      "Fort Smith",
      "Fort Walton Beach",
      "Fort Wayne",
      "Fort Worth",
      "Frederick",
      "Fremont",
      "Fresno",
      "Fullerton",
      "Gainesville",
      "Garden Grove",
      "Garland",
      "Gastonia",
      "Gilbert",
      "Glendale",
      "Grand Prairie",
      "Grand Rapids",
      "Grayslake",
      "Green Bay",
      "GreenBay",
      "Greensboro",
      "Greenville",
      "Gulfport-Biloxi",
      "Hagerstown",
      "Hampton",
      "Harlingen",
      "Harrisburg",
      "Hartford",
      "Havre de Grace",
      "Hayward",
      "Hemet",
      "Henderson",
      "Hesperia",
      "Hialeah",
      "Hickory",
      "High Point",
      "Hollywood",
      "Honolulu",
      "Houma",
      "Houston",
      "Howell",
      "Huntington",
      "Huntington Beach",
      "Huntsville",
      "Independence",
      "Indianapolis",
      "Inglewood",
      "Irvine",
      "Irving",
      "Jackson",
      "Jacksonville",
      "Jefferson",
      "Jersey City",
      "Johnson City",
      "Joliet",
      "Kailua",
      "Kalamazoo",
      "Kaneohe",
      "Kansas City",
      "Kennewick",
      "Kenosha",
      "Killeen",
      "Kissimmee",
      "Knoxville",
      "Lacey",
      "Lafayette",
      "Lake Charles",
      "Lakeland",
      "Lakewood",
      "Lancaster",
      "Lansing",
      "Laredo",
      "Las Cruces",
      "Las Vegas",
      "Layton",
      "Leominster",
      "Lewisville",
      "Lexington",
      "Lincoln",
      "Little Rock",
      "Long Beach",
      "Lorain",
      "Los Angeles",
      "Louisville",
      "Lowell",
      "Lubbock",
      "Macon",
      "Madison",
      "Manchester",
      "Marina",
      "Marysville",
      "McAllen",
      "McHenry",
      "Medford",
      "Melbourne",
      "Memphis",
      "Merced",
      "Mesa",
      "Mesquite",
      "Miami",
      "Milwaukee",
      "Minneapolis",
      "Miramar",
      "Mission Viejo",
      "Mobile",
      "Modesto",
      "Monroe",
      "Monterey",
      "Montgomery",
      "Moreno Valley",
      "Murfreesboro",
      "Murrieta",
      "Muskegon",
      "Myrtle Beach",
      "Naperville",
      "Naples",
      "Nashua",
      "Nashville",
      "New Bedford",
      "New Haven",
      "New London",
      "New Orleans",
      "New York",
      "New York City",
      "Newark",
      "Newburgh",
      "Newport News",
      "Norfolk",
      "Normal",
      "Norman",
      "North Charleston",
      "North Las Vegas",
      "North Port",
      "Norwalk",
      "Norwich",
      "Oakland",
      "Ocala",
      "Oceanside",
      "Odessa",
      "Ogden",
      "Oklahoma City",
      "Olathe",
      "Olympia",
      "Omaha",
      "Ontario",
      "Orange",
      "Orem",
      "Orlando",
      "Overland Park",
      "Oxnard",
      "Palm Bay",
      "Palm Springs",
      "Palmdale",
      "Panama City",
      "Pasadena",
      "Paterson",
      "Pembroke Pines",
      "Pensacola",
      "Peoria",
      "Philadelphia",
      "Phoenix",
      "Pittsburgh",
      "Plano",
      "Pomona",
      "Pompano Beach",
      "Port Arthur",
      "Port Orange",
      "Port Saint Lucie",
      "Port St. Lucie",
      "Portland",
      "Portsmouth",
      "Poughkeepsie",
      "Providence",
      "Provo",
      "Pueblo",
      "Punta Gorda",
      "Racine",
      "Raleigh",
      "Rancho Cucamonga",
      "Reading",
      "Redding",
      "Reno",
      "Richland",
      "Richmond",
      "Richmond County",
      "Riverside",
      "Roanoke",
      "Rochester",
      "Rockford",
      "Roseville",
      "Round Lake Beach",
      "Sacramento",
      "Saginaw",
      "Saint Louis",
      "Saint Paul",
      "Saint Petersburg",
      "Salem",
      "Salinas",
      "Salt Lake City",
      "San Antonio",
      "San Bernardino",
      "San Buenaventura",
      "San Diego",
      "San Francisco",
      "San Jose",
      "Santa Ana",
      "Santa Barbara",
      "Santa Clara",
      "Santa Clarita",
      "Santa Cruz",
      "Santa Maria",
      "Santa Rosa",
      "Sarasota",
      "Savannah",
      "Scottsdale",
      "Scranton",
      "Seaside",
      "Seattle",
      "Sebastian",
      "Shreveport",
      "Simi Valley",
      "Sioux City",
      "Sioux Falls",
      "South Bend",
      "South Lyon",
      "Spartanburg",
      "Spokane",
      "Springdale",
      "Springfield",
      "St. Louis",
      "St. Paul",
      "St. Petersburg",
      "Stamford",
      "Sterling Heights",
      "Stockton",
      "Sunnyvale",
      "Syracuse",
      "Tacoma",
      "Tallahassee",
      "Tampa",
      "Temecula",
      "Tempe",
      "Thornton",
      "Thousand Oaks",
      "Toledo",
      "Topeka",
      "Torrance",
      "Trenton",
      "Tucson",
      "Tulsa",
      "Tuscaloosa",
      "Tyler",
      "Utica",
      "Vallejo",
      "Vancouver",
      "Vero Beach",
      "Victorville",
      "Virginia Beach",
      "Visalia",
      "Waco",
      "Warren",
      "Washington",
      "Waterbury",
      "Waterloo",
      "West Covina",
      "West Valley City",
      "Westminster",
      "Wichita",
      "Wilmington",
      "Winston",
      "Winter Haven",
      "Worcester",
      "Yakima",
      "Yonkers",
      "York",
      "Youngstown"
    };
  }
}
