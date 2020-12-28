import {action, observable} from "mobx"
import ErrorMessage from "./ErrorMessage"

export default class ErrorMessageContainer {
    @observable errorMessages = {}

    @action merge = (container) => {
        this.errorMessages = {}
        for (let idx in container.messages) {
            const message = container.messages[idx]
            let error = new ErrorMessage(message.code, message.message)
            this.errorMessages[message.code] = error
        }
    }

    hasError = (code) => {
        return this.errorMessages[code] !== undefined
    }

    getFieldError = (code) => {
        return this.hasError(code) ? this.errorMessages[code].value.value : null
    }
}