import { users } from '../model';
const UserService = {
  addToUser(username, password, email, role = 'USER') {
    const newUser = { id: users.length + 1, username, password, email, role, };
    users.push(newUser);
    return newUser;
  },
  editUser(userId, updatedData) {
    const user = users.find(u => u.id === userId);
    if (user) {
      Object.assign(user, updatedData);
      return user;
    }
    return null;
  },
  deleteUser(userId) {
    const index = users.findIndex(u => u.id === userId);
    if (index !== -1) {
      users.splice(index, 1);
      return true;
    }
    return false;
  },
  getUserList() { return users; },
  getUserDetail(userId) { return users.find(u => u.id === userId) || null; },
};
export default UserService;