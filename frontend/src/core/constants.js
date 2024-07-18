export class State {
  static IDLE = 'idle';
  static LOADING = 'loading';
  static SUCCEEDED = 'succeeded';
  static FAILED = 'failed';
}

export class Roles {
  static ADMIN = 'ADMIN';
  static STUDENT = 'STUDENT';
}

export class API {
  static baseVersionedURL = 'http://localhost:8080/api/v1';
  static baseURL = 'http://localhost:8080';
}
