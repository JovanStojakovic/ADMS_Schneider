export class ProblemUser{
    constructor(
      public id: number,
      public place: String,
      public street: String,
      public number: number,
      public predictedDate: Date,
      public predictedTime: Date
    ){}
  }