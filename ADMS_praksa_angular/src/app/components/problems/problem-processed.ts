export class ProblemProcessed{
    constructor(
        public id: number,
        public place: String,
        public street: String,
        public number: number,
        public creationDate: Date,
        public creationTime: Date,
        public problemType: String,
        public electirityMeter: number,
        public comment: String,
        public status: String,
        public brWorkers: number,
        public predictedTime: String,
        public predictedDate: Date,
        public problemId: number,
    ){}
  }