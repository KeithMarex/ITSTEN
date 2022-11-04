describe('BuildingChecks', () => {
    beforeEach('Login user', () => {
        cy.visit('/');
        cy.get('#login-button').click();

        cy.get('input[type="text"]').type('test5@mail.com')
        cy.get('input[type="password"]').type('Test123!')
        cy.get('button[type="submit"]').click();
        cy.location('pathname').should("eq", "/");
    })

    it("view combat log dialog", () => {
        cy.wait(3000);
        cy.get('.combatButtonHeader').click();
        cy.get('.innerModalBox').should('exist');
    })
})
