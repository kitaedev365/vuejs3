describe('BoardPost Component', () => {
  beforeEach(() => {
    cy.visit('/')  // 기본 URL은 Cypress 설정에서 지정된 URL
  })
  it('renders the form with empty fields', () => {
    cy.get('[data-cy=title]').should('have.value', '')
    cy.get('[data-cy=content]').should('have.value', '')
  })
  it('shows an error message when fields are empty on submit', () => {
    cy.get('[data-cy=submit-button]').click()
    cy.get('[data-cy=message]').should('contain', 'Please fill out all fields.')
  })
  it('submits the post when title and content are filled', () => {
    cy.get('[data-cy=title]').type('Test Title')
    cy.get('[data-cy=content]').type('Test Content')
    cy.get('[data-cy=submit-button]').click()
    cy.get('[data-cy=message]').should('contain', 'Post created successfully!')
    cy.get('[data-cy=title]').should('have.value', '')
    cy.get('[data-cy=content]').should('have.value', '')
  })
})    