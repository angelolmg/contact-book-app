import { Contact } from './contact';
export interface ContactBook {
  id: number;
  contacts: Contact[];
  registerDate: string;
}
