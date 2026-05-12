package com.example.groceryapp.data.model

object DummyDataList{
    val products = listOf(
        Product(
            id = 1,
            name = "Chocolate Mousse Torte Cake",
            category = "Bakery",
            price = 42.95,
            image = "https://images.unsplash.com/photo-1578985545062-69928b1d9587?q=80&w=500"
        ),
        Product(
            id = 2,
            name = "Triple Chocolate Enrobed Brownie Cake",
            category = "Bakery",
            price = 39.95,
            image = "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 3,
            name = "Reddi Wip Dairy Whipped Topping",
            category = "Dairy",
            price = 3.19,
            image = "https://images.unsplash.com/photo-1553909489-ec2175ef3f52?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 4,
            name = "Red Velvet Chocolate Cake",
            category = "Bakery",
            price = 42.95,
            image = "https://www.freshsavory.com/wp-content/uploads/2025/09/red-velvet-cake.jpg"
        ),
        Product(
            id = 5,
            name = "Rice Dream Original Non-Dairy Beverage",
            category = "Dairy",
            price = 5.19,
            image = "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcRA87ypLj5Eu6kHr1mWA8Im3rOcsf-zrf144sfBPQgMOFBHNSfUuxHCFnirzuNGvp7Gv_kT75VqqfKfDGKXfeP38S2_AMxcy2L0NbUj4Bla6T851aF5RC0_MZk2tUcCLCJGq0OeSg&usqp=CAc"
        ),
        Product(
            id = 6,
            name = "Member's Mark Mini Bagels",
            category = "Bakery",
            price = 20.99,
            image = "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 7,
            name = "Daiya Black Cherry Yogurt",
            category = "Dairy",
            price = 1.95,
            image = "https://images.unsplash.com/photo-1488477181946-6428a0291777?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 8,
            name = "Daily Chef Mini Candy Cookies",
            category = "Bakery",
            price = 28.99,
            image = "https://images.unsplash.com/photo-1495147466023-ac5c588e2e94?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 9,
            name = "So Delicious Vanilla Yogurt",
            category = "Dairy",
            price = 2.09,
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo24bdQ3Y7D02u6ONCfHDnLRB47vnjvlPFxw&s"),

        Product(
            id = 10,
            name = "Gaston’s Bakery Puff Pastry",
            category = "Bakery",
            price = 39.95,
            image = "https://images.unsplash.com/photo-1555507036-ab1f4038808a?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 11,
            name = "Signature Bakery Assortment",
            category = "Bakery",
            price = 59.99,
            image = "https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 12,
            name = "Gaston’s Bakery Croissants",
            category = "Bakery",
            price = 34.95,
            image = "https://images.unsplash.com/photo-1555507036-ab1f4038808a?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 13,
            name = "Daiya Dairy Free Yogurt",
            category = "Dairy",
            price = 1.92,
            image = "https://images.unsplash.com/photo-1559598467-f8b76c8155d0?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 14,
            name = "Celeste Pizza For One",
            category = "Frozen",
            price = 1.00,
            image = "https://images.unsplash.com/photo-1513104890138-7c749659a591?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 15,
            name = "Nestle Coffee-mate Creamer",
            category = "Dairy",
            price = 6.99,
            image = "https://images.unsplash.com/photo-1564419320461-6870880221ad?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 16,
            name = "Nature's Promise Almond Milk",
            category = "Dairy",
            price = 2.02,
            image = "https://images.unsplash.com/photo-1553909489-ec2175ef3f52?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 17,
            name = "Weight Watchers Pepperoni Pizza",
            category = "Frozen",
            price = 2.39,
            image = "https://images.unsplash.com/photo-1594007654729-407eedc4be65?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 18,
            name = "Special K Breakfast Sandwich",
            category = "Frozen",
            price = 7.49,
            image = "https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&q=80&w=500"
        ),
        Product(
            id = 19,
            name = "Eggo Chocolate Chip Waffles",
            category = "Frozen",
            price = 3.32,
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWH4pxLp0VIc0Ou04fmbgyPgR6I1o6lu4AJg&s"
        ),
        Product(
            id = 20,
            name = "Deep Cocktail Samosa",
            category = "Frozen",
            price = 4.99,
            image = "https://images.unsplash.com/photo-1601050690597-df0568f70950?auto=format&fit=crop&q=80&w=500"
        )
    )

    val categories = listOf(
        "All",
        "Bakery",
        "Dairy",
        "Frozen"
    )
}