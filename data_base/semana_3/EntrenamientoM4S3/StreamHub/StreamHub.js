// ==============================================
//   MongoDB Project – StreamHub Database Script
// ==============================================

// ----------------------
// DATABASE SELECTION
// ----------------------
use streamhub;


// ----------------------
// COLLECTION: users
// ----------------------
db.createCollection("users");

db.users.insertOne({
  nombre: "ricardo",
  email: "ric@gmail.com",
  country: "colombia"
});

db.users.insertOne({
  id: "a3432476",
  nombre: "ricardo",
  email: "ric@gmail.com",
  country: "colombia",
  prefergenre: ["horror", "comedy", "family"]
});

db.users.insertMany([
  {
    id: "m3432477",
    nombre: "laura",
    email: "laura@gmail.com",
    country: "mexico",
    prefergenre: ["drama", "romance", "comedy"]
  },
  {
    id: "a3432478",
    nombre: "carlos",
    email: "carlos@gmail.com",
    country: "argentina",
    prefergenre: ["action", "thriller", "horror"]
  },
  {
    id: "a3432479",
    nombre: "andrea",
    email: "andrea@gmail.com",
    country: "chile",
    prefergenre: ["fantasy", "family", "animation"]
  },
  {
    id: "p3432480",
    nombre: "juan",
    email: "juan@gmail.com",
    country: "peru",
    prefergenre: ["sci-fi", "action", "adventure"]
  },
  {
    id: "a3432481",
    nombre: "sofia",
    email: "sofia@gmail.com",
    country: "colombia",
    prefergenre: ["documentary", "biography", "history"]
  },
  {
    id: "s3432482",
    nombre: "miguel",
    email: "miguel@gmail.com",
    country: "spain",
    prefergenre: ["drama", "comedy", "romance"]
  },
  {
    id: "u3432483",
    nombre: "valentina",
    email: "valentina@gmail.com",
    country: "uruguay",
    prefergenre: ["animation", "family", "fantasy"]
  },
  {
    id: "v3432484",
    nombre: "daniel",
    email: "daniel@gmail.com",
    country: "venezuela",
    prefergenre: ["thriller", "crime", "mystery"]
  },
  {
    id: "c3432485",
    nombre: "camila",
    email: "camila@gmail.com",
    country: "costa rica",
    prefergenre: ["romance", "drama", "comedy"]
  },
  {
    id: "e3432486",
    nombre: "andres",
    email: "andres@gmail.com",
    country: "ecuador",
    prefergenre: ["action", "adventure", "sci-fi"]
  },
  {
    id: "p3432487",
    nombre: "maria",
    email: "maria@gmail.com",
    country: "paraguay",
    prefergenre: ["musical", "family", "comedy"]
  }
]);


// ----------------------
// COLLECTION: media
// ----------------------
db.createCollection("media");

db.media.insertOne({
  _id: 1,
  type: "movie",
  title: "theshining",
  duration: 146,
  genres: ["horror", "thriller"],
  cast: ["jack", "shelley", "danny"]
});

db.media.insertMany([
  { _id: 2, type: "movie", title: "inception", duration: 148, genres: ["sci-fi", "thriller"], cast: ["leonardo", "joseph", "ellen"] },
  { _id: 3, type: "movie", title: "parasite", duration: 132, genres: ["drama", "thriller"], cast: ["song", "cho", "park"] },
  { _id: 4, type: "movie", title: "interstellar", duration: 169, genres: ["sci-fi", "drama"], cast: ["matthew", "anne", "jessica"] },
  { _id: 5, type: "movie", title: "getout", duration: 104, genres: ["horror", "mystery"], cast: ["daniel", "allison", "bradley"] },
  { _id: 6, type: "movie", title: "whiplash", duration: 107, genres: ["drama", "music"], cast: ["miles", "jk", "paul"] },
  { _id: 7, type: "movie", title: "madmaxfuryroad", duration: 120, genres: ["action", "sci-fi"], cast: ["tom", "charlize", "nicholas"] },
  { _id: 8, type: "movie", title: "hereditary", duration: 127, genres: ["horror", "drama"], cast: ["toni", "alex", "milly"] },
  { _id: 9, type: "movie", title: "joker", duration: 122, genres: ["drama", "crime"], cast: ["joaquin", "robert", "zazie"] },
  { _id: 10, type: "movie", title: "bladeRunner2049", duration: 164, genres: ["sci-fi", "mystery"], cast: ["ryan", "harrison", "ana"] }
]);


// ----------------------
// COLLECTION: ranking
// ----------------------
db.createCollection("ranking");

db.ranking.insertOne({ user_id: "a3432476", media_id: 1, score: 4 });

db.ranking.insertMany([
  { user_id: "a3432476", media_id: 5, score: 5 },
  { user_id: "m3432477", media_id: 3, score: 4 },
  { user_id: "a3432478", media_id: 7, score: 5 },
  { user_id: "a3432479", media_id: 8, score: 3 },
  { user_id: "p3432480", media_id: 4, score: 5 },
  { user_id: "a3432481", media_id: 10, score: 4 },
  { user_id: "s3432482", media_id: 6, score: 5 },
  { user_id: "u3432483", media_id: 9, score: 4 },
  { user_id: "v3432484", media_id: 2, score: 5 },
  { user_id: "c3432485", media_id: 3, score: 3 },
  { user_id: "e3432486", media_id: 4, score: 5 },
  { user_id: "p3432487", media_id: 6, score: 4 }
]);


// ----------------------
// COLLECTION: history
// ----------------------
db.createCollection("history");

db.history.insertOne({
  user_id: "a3432476",
  media_id: 1,
  date: ISODate("2025-08-01T20:00:00Z"),
  durationWatched: 120
});

db.history.insertMany([
  { user_id: "m3432477", media_id: 3, date: ISODate("2025-08-02T18:30:00Z"), durationWatched: 132 },
  { user_id: "a3432478", media_id: 7, date: ISODate("2025-08-01T21:15:00Z"), durationWatched: 115 },
  { user_id: "a3432479", media_id: 8, date: ISODate("2025-08-03T17:00:00Z"), durationWatched: 90 },
  { user_id: "p3432480", media_id: 4, date: ISODate("2025-08-04T19:00:00Z"), durationWatched: 169 },
  { user_id: "a3432481", media_id: 10, date: ISODate("2025-07-31T20:45:00Z"), durationWatched: 150 },
  { user_id: "s3432482", media_id: 6, date: ISODate("2025-08-01T22:00:00Z"), durationWatched: 107 },
  { user_id: "u3432483", media_id: 9, date: ISODate("2025-08-02T16:15:00Z"), durationWatched: 122 },
  { user_id: "v3432484", media_id: 2, date: ISODate("2025-08-03T14:00:00Z"), durationWatched: 148 },
  { user_id: "c3432485", media_id: 3, date: ISODate("2025-08-04T17:00:00Z"), durationWatched: 100 },
  { user_id: "e3432486", media_id: 4, date: ISODate("2025-08-05T13:30:00Z"), durationWatched: 169 },
  { user_id: "p3432487", media_id: 5, date: ISODate("2025-08-04T20:00:00Z"), durationWatched: 104 },
  { user_id: "a3432476", media_id: 6, date: ISODate("2025-08-05T11:00:00Z"), durationWatched: 85 }
]);


// ----------------------
// COLLECTION: playlists
// ----------------------
db.createCollection("playlists");

db.playlists.insertMany([
  { user_id: "a3432476", name: "ricardo'sfavorites", created_at: ISODate("2025-08-01T10:00:00Z"), media_ids: [1, 5, 6] },
  { user_id: "m3432477", name: "laura'schill", created_at: ISODate("2025-07-20T12:00:00Z"), media_ids: [3, 9] },
  { user_id: "a3432478", name: "carlosactionhits", created_at: ISODate("2025-06-15T08:30:00Z"), media_ids: [7, 4] },
  { user_id: "p3432480", name: "juansci-fiadventure", created_at: ISODate("2025-07-05T14:45:00Z"), media_ids: [2, 4, 7] }
]);


// ----------------------
// COLLECTION: comments
// ----------------------
db.createCollection("comments");

db.comments.insertMany([
  { user_id: "a3432476", media_id: 1, comment: "greatmovie!lovedthesuspense", date: ISODate("2025-08-02T15:00:00Z") },
  { user_id: "m3432477", media_id: 3, comment: "amazingplottwists!", date: ISODate("2025-08-03T18:00:00Z") },
  { user_id: "v3432484", media_id: 2, comment: "inceptionisamind-bender,highlyrecommend", date: ISODate("2025-08-04T20:00:00Z") },
  { user_id: "s3432482", media_id: 6, comment: "whiplash'ssoundtrackwasintense!", date: ISODate("2025-08-05T10:30:00Z") }
]);


// ----------------------------------
// Example Queries, Updates & Indexes
// ----------------------------------

// Filters
db.media.find({ duration: { $gt: 90 } });
db.media.find({ duration: { $lt: 110 } });
db.users.find({ country: "colombia" });
db.media.find({ genres: { $in: ["mystery", "drama"] } });
db.media.find({ $and: [{ duration: { $gt: 120 } }, { genres: "mystery" }] });
db.users.find({ $or: [{ country: "spain" }, { prefergenre: "horror" }] });
db.comments.find({ comment: { $regex: /plot/i } });
db.comments.find({ comment: { $regex: /!$/ } });
db.comments.find({ comment: { $regex: /^amazing/i } });

// Updates
db.ranking.updateOne({ user_id: "a3432476", media_id: 1 }, { $set: { score: 5 } });
db.ranking.updateMany({ score: 3 }, { $inc: { score: 1 } });
db.ranking.updateMany(
  { score: { $lt: 4 } },
  { $set: { status: "improved" }, $inc: { score: 1, views: 1 } }
);

// Deletions
db.comments.deleteOne({
  user_id: "v3432484",
  media_id: 2,
  comment: "mind-blowing!"
});

db.comments.deleteMany({
  comment: { $regex: /whiplash/i }
});

// Indexing
db.comments.createIndex({ user_id: 1 });
db.ranking.createIndex({ media_id: 1 });

// Aggregation
db.media.aggregate([
  { $unwind: "$genres" },
  { $group: { _id: "$genres", count: { $sum: 1 } } },
  { $match: { count: { $gt: 1 } } },
  {
    $project: {
      genre: "$_id",
      count: 1,
      popular: { $gt: ["$count", 2] },
      _id: 0
    }
  },
  { $sort: { count: -1 } },
  { $limit: 3 }
]).pretty();

db.history.aggregate([
  { $group: { _id: "$user_id", count: { $sum: 1 } } },
  { $match: { count: { $gt: 5 } } }
]);

// Aggregation
// USED TO separate genres - $unwind
db.media.aggregate([
  { $unwind: "$genres" }
]).pretty();

// USED TO count how many movies per genre - $group
db.media.aggregate([
  { $unwind: "$genres" },
  { $group: { _id: "$genres", count: { $sum: 1 } } }
]).pretty();

// USED TO filter genres with more than 1 movie - $match
db.media.aggregate([
  { $unwind: "$genres" },
  { $group: { _id: "$genres", count: { $sum: 1 } } },
  { $match: { count: { $gt: 1 } } }
]).pretty();

// USED TO rename fields and add a new one - $project
db.media.aggregate([
  { $unwind: "$genres" },
  { $group: { _id: "$genres", count: { $sum: 1 } } },
  {
    $project: {
      genre: "$_id",
      count: 1,
      popular: { $gt: ["$count", 2] },
      _id: 0
    }
  }
]).pretty();

// USED TO sort by count (from highest to lowest) - $sort
db.media.aggregate([
  { $unwind: "$genres" },
  { $group: { _id: "$genres", count: { $sum: 1 } } },
  {
    $project: {
      genre: "$_id",
      count: 1,
      popular: { $gt: ["$count", 2] },
      _id: 0
    }
  },
  { $sort: { count: -1 } }
]).pretty();

// USED TO show only the top 3 - $limit
db.media.aggregate([
  { $unwind: "$genres" },
  { $group: { _id: "$genres", count: { $sum: 1 } } },
  {
    $project: {
      genre: "$_id",
      count: 1,
      popular: { $gt: ["$count", 2] },
      _id: 0
    }
  },
  { $sort: { count: -1 } },
  { $limit: 3 }
]).pretty();