import { Inter } from "next/font/google";
import './style.css';

const inter = Inter({ subsets: ["latin"] });

export const metadata = {
  title: "Home - Cabeleleila Leila",
  description: "Login page",
};

export default function HomeLayout({ children }) {
    return (
        <html lang="en">
            <body className={inter.className}>{children}</body>
        </html>
    );
}
