import { ApolloServer } from "@apollo/server";
import { startStandaloneServer } from "@apollo/server/standalone";
import { typeDefs } from "./schema.js";
import db from "./_db.js";
import { time } from "node:console";

const resolvers = {
    Query: {
        games() {
            return db.games;
        },

        reviews() {
            return db.reviews;
        },

        authors() {
            return db.authors;
        },
        //the below resolver function is used to return a specific review based on
        //the id provided in the query
        //first argument is the parent object, which is not used in this case,
        //so we can ignore it with an underscore
        //second argument is the args object, which contains the arguments passed in the query
        //There is one more argument called context,
        //which can be used to pass additional information to the resolver,
        //such as authentication details or database connections.
        //We are not using it in this example, so we can ignore it as well.
        review(_, args) {
            return db.reviews.find((review) => review.id === args.id);
        },
        author(_, args) {
            return db.authors.find((author) => author.id === args.id);
        },
        game(_, args) {
            return db.games.find((game) => game.id === args.id);
        },
    },

    Game: {
        reviews(parent) {
            return db.reviews.filter((review) => review.game_id === parent.id);
        },
    },

    Author: {
        reviews(parent) {
            return db.reviews.filter((review) => review.author_id === parent.id);
        },
    },

    Review: {
        game(parent) {
            return db.games.find((game) => game.id === parent.game_id);
        },
        author(parent) {
            return db.authors.find((author) => author.id === parent.author_id);
        },
    },

    Mutation: {
        deleteGame(_, args) {
            db.games = db.games.filter((game) => game.id !== args.id);
            return db.games;
        },

        addGame(_, args) {
            let max = 1;

            for(let game of db.games) {
                max = max > parseInt(game.id) ? max : parseInt(game.id);
            }

            const newGame = {
                id: (max + 1).toString(),
                title: args.input.title,
                platform: args.input.platform,
            };
            db.games.push(newGame);
            return db.games;
        },

        updateGame(_, args) {
            db.games = db.games.map((game) => {
                if(game.id === args.id) {
                    return {
                        id: game.id,
                        title: args.edit.title ? args.edit.title : game.title,
                        platform: args.edit.platform ? args.edit.platform : game.platform,
                    };
                }
                return game;
            });
            return db.games;
        }
    },
};

//server setup
const server = new ApolloServer({
    //typeDefs, - types of data that we want to query
    //resolvers, - these are functions that will return the data for each query, mutation,
    //or subscription defined in our schema

    typeDefs,
    resolvers,
});

const url = await startStandaloneServer(server, {
    listen: { port: 4000 },
});

console.log(`Server ready at ${url.toString()}`);
