import axios from 'axios';
import { toast } from 'react-toastify';
import icon from '../../assets/img/notification-icon.svg';
import { BASE_URL } from "../../utils/request";
import './styles.css';

type Props = {
    saleId: number;
}
/**Função que irá chamar a requisição API com o parâmetro definido na função*/
function handClick(id: number) {
    /**"Axios" biblioteca para fazer a requisição de dados no back end da aplicação. Devemos passar
     * o endereço URL definido nas variáveis de ambiente e se o retorno for true, esse retorno será
     * armazenado na variárel "response" do ".then". Toda vez que quisermos o retorno verdadeiro de
     * uma função, deveremos utilizar o ".then".
    */
    axios(`${BASE_URL}/sales/${id}/notification`)
        .then(response => {
            toast.info("SMS enviado com sucesso");
        })
}

function NotificationButton({ saleId }: Props) {
    return (
        /**"onClick" atribuindo ação de click ao botão. Essa ação necessita como argumento uma função
         * "()" => nome da função (parâmetro).
        */
        <div className="dsmeta-red-btn" onClick={() => handClick(saleId)}>
            <img src={icon} alt="Notificar" />
        </div>
    )

}
export default NotificationButton;