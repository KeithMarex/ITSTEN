describe('BuildingChecks', () => {
    beforeEach('Login user', () => {
        cy.visit('/');
        cy.get('#login-button').click();

        cy.get('input[type="text"]').type('test5@mail.com')
        cy.get('input[type="password"]').type('Test123!')
        cy.get('button[type="submit"]').click();
        cy.location('pathname').should("eq", "/");
    })

    it("user can logout", () => {
        cy.wait(3000); // wait for animations to finish
        cy.get('.settingsButton').click();
        cy.get('.settingsButtonsList > :nth-child(3)').click();
        cy.location('pathname').should("eq", "/login");
    })
})
