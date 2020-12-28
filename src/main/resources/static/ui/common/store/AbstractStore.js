import {observable} from "mobx"
import ErrorMessageContainer from "../error/ErrorMessageContainer"

export default class AbstractStore {

    @observable messageContainer = new ErrorMessageContainer()

}