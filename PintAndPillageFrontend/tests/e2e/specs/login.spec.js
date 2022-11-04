beforeEach(() => {
    cy.visit('/');
})

describe("LoginTest", () => {
    it("Logs a seeded user in", () => {
        cy.visit('/');
        cy.get('#login-button').click();

        cy.get('input[type="text"]').type('test5@mail.com');
        cy.get('input[type="password"]').type('Test123!');
        cy.get('button[type="submit"]').click();
        cy.location('pathname').should("eq", "/");
    })

    it("Tries to login with invalid credentials, will be returned to 401 page", () => {
        cy.visit('/');
        cy.get('#login-button').click();

        cy.get('input[type="text"]').type('test5@madil.com');
        cy.get('input[type="password"]').type('Test123!');
        cy.get('button[type="submit"]').click();
        cy.contains('Something went wrong')
    })

    it("check if the city name for test5@mail.com shows", () => {
        cy.visit('/');
        cy.get('#login-button').click();

        cy.get('input[type="text"]').type('test5@mail.com');
        cy.get('input[type="password"]').type('Test123!');
        cy.get('button[type="submit"]').click();

        cy.get('.villageSelector > p').should('exist');
    })
})
