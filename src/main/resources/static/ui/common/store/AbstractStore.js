import {makeObservable, observable} from "mobx"
import ErrorMessageContainer from "../error/ErrorMessageContainer"

export default class AbstractStore {

    messageContainer = new ErrorMessageContainer()

    constructor() {
        makeObservable(this, {
            messageContainer: observable
        })
    }

}