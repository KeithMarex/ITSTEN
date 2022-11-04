beforeEach(() => {
    cy.visit('/resetpassword');
})

describe("ResetPasswordTest", () => {
    // it("Logs a seeded user in", () => {
    //     cy.visit('/');
    //     cy.get('#login-button').click();
    //
    //     cy.get('input[type="text"]').type('test5@mail.com');
    //     cy.get('input[type="password"]').type('Test123!');
    //     cy.get('button[type="submit"]').click();
    //     cy.location('pathname').should("eq", "/");
    // })
    //
    // it("Tries to login with invalid credentials, will be returned to 401 page", () => {
    //     cy.visit('/');
    //     cy.get('#login-button').click();
    //
    //     cy.get('input[type="text"]').type('test5@madil.com');
    //     cy.get('input[type="password"]').type('Test123!');
    //     cy.get('button[type="submit"]').click();
    //     cy.contains('Something went wrong')
    // })
    const randomEmail = () => `test${Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)}@gmail.com`
    it('should not complete when invalid email has been entered', () => {
        cy.get('.inputField').type('blablablasdd');
        cy.get('.submitButton').click();
        cy.contains('Email is invalid');
    })

    it('should not complete when valid email, but not existing user', () => {
        cy.get('.inputField').type('test5@mail.commmmmm');
        cy.get('.submitButton').click();
        cy.contains('Something went wrong');
    })

    it('should send a password reset successfully', () => {
        cy.get('.inputField').type('test5@mail.com');
        cy.get('.submitButton').click();
        cy.location('pathname').should("eq", "/login");
    })
})
