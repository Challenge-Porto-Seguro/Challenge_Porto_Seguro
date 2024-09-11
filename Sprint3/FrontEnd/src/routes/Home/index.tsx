import { HomeStyle } from "../../styledConfig"
import Footer from "../../components/Footer"
import Introducao from "./Introducao"
import SobreNos from "./SobreNos"

export default function Home() {
    document.title = "Home"
    return (
        <HomeStyle>
            <Introducao />
            <SobreNos />
            <Footer />
        </HomeStyle>
    )
}