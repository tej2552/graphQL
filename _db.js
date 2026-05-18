// db.js

const db = {
    games: [
        {
            id: "1",
            title: "Sekiro",
            platform: ["PS5", "PC"],
        },
        {
            id: "2",
            title: "RDR2",
            platform: ["PS5", "Xbox", "PC"],
        },
        {
            id: "3",
            title: "Dark Souls 3",
            platform: ["PS5", "Xbox"],
        },
        {
            id: "4",
            title: "Dark Souls 1",
            platform: ["PC", "Mobile", "Xbox"],
        },
        {
            id: "5",
            title: "Valorant",
            platform: ["PC"],
        },
    ],

    reviews: [
        {
            id: "101",
            rating: 9,
            comment: "Amazing gameplay and visuals",
            game_id: "1",
            author_id: "201",
        },
        {
            id: "102",
            rating: 8,
            comment: "Very addictive multiplayer",
            game_id: "5",
            author_id: "202",
        },
        {
            id: "103",
            rating: 7,
            comment: "Good but repetitive at times",
            game_id: "3",
            author_id: "203",
        },
        {
            id: "104",
            rating: 10,
            comment: "Masterpiece",
            game_id: "2",
            author_id: "204",
        },
        {
            id: "105",
            rating: 6,
            comment: "Could improve performance",
            game_id: "4",
            author_id: "205",
        },
        {
            id: "106",
            rating: 10,
            comment: ":) :) :)",
            game_id: "1",
            author_id: "201",
        },
    ],

    authors: [
        {
            id: "201",
            name: "Tejas",
            verified: true,
        },
        {
            id: "202",
            name: "Rahul",
            verified: false,
        },
        {
            id: "203",
            name: "Ananya",
            verified: true,
        },
        {
            id: "204",
            name: "Kiran",
            verified: false,
        },
        {
            id: "205",
            name: "Shreya",
            verified: true,
        },
    ],
};

export default db;
