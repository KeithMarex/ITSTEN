import { mount } from '@vue/test-utils'
import Login from '../../src/components/authentication/Login'

describe('Login component', () => {
    let wrapper = null;

    beforeEach(async () => {
        wrapper = mount(Login, {
            mocks: {
                $store: {
                    dispatch: jest.fn(),
                },
            },
            methods: {
                isCompatibleBrowser: () => true,
            },
        });
    })

    it('renders login form when browser is compatible', () => {
        expect(wrapper.find('.login').exists()).toBe(true)
    })

    it('renders incompatible browser message when browser is not compatible', () => {
        const wrapper = mount(Login, {
            mocks: {
                $store: {
                    dispatch: jest.fn(),
                },
            },
            methods: {
                isCompatibleBrowser: () => false,
            },
        })

        expect(wrapper.find('.uncompatibleBrowser').exists()).toBe(true)
    })

    it('calls login method when submit button is clicked', async () => {
        wrapper.find('.submitButton').trigger('click')
        await wrapper.vm.$nextTick()

        expect(wrapper.vm.$store.dispatch).toHaveBeenCalledWith('login', {
            username: '',
            password: '',
        })
    })

    it('emits updateRoute event when reset password link is clicked', async () => {
        wrapper.find('#resPass').trigger('click')
        await wrapper.vm.$nextTick()

        expect(wrapper.emitted().updateRoute).toBeTruthy()
        expect(wrapper.emitted().updateRoute[0]).toEqual(['ResetPassword'])
    })

    it('emits updateRoute event when create new account link is clicked', async () => {
        wrapper.find('#create-account').trigger('click')
        await wrapper.vm.$nextTick()

        expect(wrapper.emitted().updateRoute).toBeTruthy()
        expect(wrapper.emitted().updateRoute[0]).toEqual(['Register'])
    })
})
