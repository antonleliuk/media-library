import {action, observable} from "mobx"
import AbstractStore from "../../../common/store/AbstractStore"
import FormValue from "../../../common/model/FormValue"

export default class LoginStore extends AbstractStore {

    @observable login = new FormValue()
    @observable password = new FormValue()

    @action loginUser = (fetchApi) => {
        const form = new URLSearchParams()
        form.append("username", this.login.value)
        form.append("password", this.password.value)
        fetchApi.submitForm('login-process', form)
            .then(response => {
                if (response.errors) {
                    this.messageContainer.merge(response.errors)
                }
                // TODO redirect to the dashboard
            })
    }
}