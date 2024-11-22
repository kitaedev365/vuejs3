import { reactive } from 'vue'
import axios from 'axios'
const state = reactive({
  tasks: [],
  loading: false,
  error: null
})
const actions = {
  async fetchTasks() {
    state.loading = true
    try {
        const response = await axios.get('https://jsonplaceholder.typicode.com/todos?_limit=10')
      state.tasks = response.data
    } catch (err) {
      state.error = err
    } finally {
      state.loading = false
    }
  },
}
export default {
  state,
  actions,
  get tasks() {
    return state.tasks
  },
  get loading() {
    return state.loading
  },
  get error() {
    return state.error
  }
}