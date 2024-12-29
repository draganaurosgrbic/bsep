import { Role } from './role';

export class User {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  enabled: boolean;
  activationLink: string;
  activationExpiration: Date;
  roles: Role[];
}
