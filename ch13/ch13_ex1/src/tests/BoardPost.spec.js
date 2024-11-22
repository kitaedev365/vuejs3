import { mount } from '@vue/test-utils'
import BoardPost from '@/components/BoardPost.vue'
describe('BoardPost.vue', () => {
  it('renders the form with empty fields', () => {
    const wrapper = mount(BoardPost)
    expect(wrapper.find('input').element.value).toBe('')
    expect(wrapper.find('textarea').element.value).toBe('')
  })
  it('displays an error message if fields are empty on submit', async () => {
    const wrapper = mount(BoardPost)
    await wrapper.find('form').trigger('submit.prevent')
    expect(wrapper.find('p').text()).toBe('Please fill out all fields.')
  })
  it('submits the post when title and content are filled', async () => {
    const wrapper = mount(BoardPost)
    await wrapper.find('input').setValue('Test Title')
    await wrapper.find('textarea').setValue('Test Content')
    await wrapper.find('form').trigger('submit.prevent')
    expect(wrapper.find('p').text()).toBe('Post created successfully!')
    expect(wrapper.find('input').element.value).toBe('')
    expect(wrapper.find('textarea').element.value).toBe('')
  })
})