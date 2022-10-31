// https://docs.cypress.io/api/table-of-contents

// describe('My First Test', () => {
//   it('Visits the app root url', () => {
//     cy.visit('/')
//     cy.contains('h1', 'Welcome Viking!')
//   })
// })

describe('Seeded user can login', () => {
  it("Logs a seeded user in", () => {
    cy.visit('/');
    cy.get('#login-button').click();

    cy.get('input[type="text"]').type('test5@mail.com')
    cy.get('input[type="password"]').type('Test123!')
    cy.get('button[type="submit"]').click();
    cy.location('pathname').should("eq", "/");
  })
})

describe('Register user', () => {
  it("Tries to register a new user", () => {
    cy.visit('/');
    cy.get('#login-button').click();
    cy.get('#create-account').click();

    // cy.get('input[type="text"]').type('test5@mail.com')
    // cy.get('input[type="password"]').type('Test123!')
    // cy.get('button[type="submit"]').click();
    // cy.location('pathname').should("eq", "/");
  })
})

describe('Build a house', () => {
  before('Login user', () => {
    cy.visit('/');
    cy.get('#login-button').click();

    cy.get('input[type="text"]').type('test5@mail.com')
    cy.get('input[type="password"]').type('Test123!')
    cy.get('button[type="submit"]').click();
    cy.location('pathname').should("eq", "/");
  })

  it('Build a house', () => {
    cy.get('.clickableTile').eq(3).click();
    cy.get('.buildingInformationContainer').get('h1')
        .contains("House")
        .first()
        .parent('.buildingInformationContainer')
        .parent('.buildingListItemContainer')
        .children('button').click();
    cy.wait(15000).get('clickableTile').eq(3).click();
    cy.get('.populationTitleContainer').get('h1')
        .contains("House");
  })
})
