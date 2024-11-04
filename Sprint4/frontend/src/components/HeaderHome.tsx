import Image from "next/image";
import portoLogo from "@/imagem/logo_porto.webp"

export default function HeaderHome() {
  return(
    <header className="flex justify-center border-2 border-b-black bg-blue-200">
        <Image className="w-60" src={portoLogo} alt="logo da porto" />
    </header>
  )
}