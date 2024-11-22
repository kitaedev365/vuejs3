import { mount } from '@vue/test-utils'
import BoardPost from '../components/BoardPost.vue'

describe('BoardPost.vue', () => {
  it('renders properly', () => {
    const wrapper = mount(BoardPost)
    expect(wrapper.find('label[for="title"]').text()).toBe('Title:')
    expect(wrapper.find('label[for="content"]').text()).toBe('Content:')
  })

  it('shows error message when fields are empty', async () => {
    const wrapper = mount(BoardPost)
    await wrapper.find('button').trigger('submit')
    expect(wrapper.text()).toContain('Please fill out all fields.')
  })

  it('shows success message when fields are filled', async () => {
    const wrapper = mount(BoardPost)
    await wrapper.find('input#title').setValue('Test Title')
    await wrapper.find('textarea#content').setValue('Test Content')
    await wrapper.find('button').trigger('submit')
    expect(wrapper.text()).toContain('Post created successfully!')
  })
})