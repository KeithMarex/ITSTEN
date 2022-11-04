describe('BuildingChecks', () => {
    beforeEach('Login user', () => {
        cy.visit('/');
        cy.get('#login-button').click();

        cy.get('input[type="text"]').type('test5@mail.com')
        cy.get('input[type="password"]').type('Test123!')
        cy.get('button[type="submit"]').click();
        cy.location('pathname').should("eq", "/");
    })

    it("Checks that a headquarters cannot be removed", () => {
        cy.get('.clickableTile > img.headQuarters').parent().click({force: true});
        cy.get('.removeButton').click();
        cy.get('.dg-btn--ok').click();
        cy.get('#modalButton').click();

        cy.get('.clickableTile > img.headQuarters').should('exist')
    })

    it('Can build a wall at pos 0.0', () => {
        cy.get('.clickableTile').eq(0).click();
        cy.get('.buildingInformationContainer').get('h1')
            .contains("Wall")
            .first()
            .parent('.buildingInformationContainer')
            .parent('.buildingListItemContainer')
            .children('button').click();
    })

    it('wall has been built at pos 0.0', () => {
        cy.get('.clickableTile').eq(0).click();
        cy.contains('Defence bonus');
    })
})
