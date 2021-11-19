# MyFood

Aplicativo que simula visualmente o IFood.

## Sobre mim

Iniciei meus estudos de programação em março de 2020, por sugestão de um amigo da área. Com o tempo. fui me interessando cada vez mais pelo assunto, e em fevereiro de 2021, passei a estudar desenvolvimento de apps através do Android Studio associado à linguagem Kotlin.

Estou em busca de uma oportunidade onde eu possa aplicar os conhecimentos que obtive, pelos cursos que fiz e pelas orientações que me foram passadas durante a minha jornada de estudos. Busco uma empresa onde eu possa aprender muito e entregar o melhor resultado possível.

## O projeto

O projeto é uma simulação de parte da interface gráfica da página inicial do IFood, conforme representado na imagem abaixo.

![screenshot](https://raw.githubusercontent.com/lmtmoraes/MyFood/main/intro.jpg)

Com características muito semelhantes às do app original, sua idealização viabiliza o conhecimento de técnicas e recursos que podem ser aplicados no cotidiano de um programador Android.

## Tecnologias usadas

Essas são as principais tecnologias usadas nessa aplicação:

| Biblioteca     | Função                                                    |
| -------------- | --------------------------------------------------------- |
| [Picasso]      | Biblioteca para facilitar o download de imagens externas. |
| [ATViewHolder] | Biblioteca para facilitar a criação de Adapters.          |

## Funcionamento

Começando do topo, a barra de endereço nada mais é do que um botão customizado. Já o Q.R. Code, é uma imagem do tipo Vector, extraída da própria ferramenta. A vantagem da utilização de imagens desse tipo, é que elas são facilmente adaptáveis, sendo possível alterar seu tamanho, forma e cor sem prejudicar sua resolução, pois não são formadas por pixels.

Por questões didáticas, o funcionamento se limita à algumas poucas interações.

Dadas as limitações mencionadas, a criação do app conta com técnicas e recursos de extrema utilidade para um desenvolvedor Android, como por exemplo, o uso de um TabLayout associado a um ViewPager. Essa interação se dá através de fragmentos, especificados na MainActivity. Neste app, o único fragmento a ser preenchido, é o RestaurantFragment, para ilustrar todo o conteúdo referente à relacionado à aba “Restaurantes”, que faz parte do TabLayout. Dentro do fragmento em questão, é organizada toda a lógica de interação envolvendo a parte interna do ViewPager, onde estão presentes os filtros, categorias, banners de ofertas e restaurantes famosos. As Views destes campo são RecyclerViews.

O componente ChipGroup é responsável pela organização dos filtros. ChipGroups são conjuntos de elementos (chips) com características muito semelhantes às de um botão. Para os chips que possuem ícones, foram utilizadas imagens do tipo Vector, fornecidas pelo Android Studio.

Graças a utilização de Data Binding no app, uma data class foi criada para cada arquivo de View. A biblioteca ‘ATViewHolder’ possibilita que apenas um Adapter seja criado para todas as Views. Para isso, a biblioteca faz o uso de generics. Já a biblioteca ‘Picasso’ é utilizada dentro dos arquivos de View para facilitar a importação de imagens da internet.

Uma das técnicas mais legais do projeto, é a de associar os pontos abaixo dos banners de oferta com a rolagem dos mesmos. Para a composição dos pontos, cria-se uma ArrayList com o tamanho passado como parâmetro na função addDots. A customização dos botões é dinâmica, graças à função addOnScrollListener, que permite que as cores dos pontos alterem entre cinza e preto conforme o movimento de rolagem e a posição de cada banner.

[picasso]: https://github.com/square/picasso
[atviewholder]: https://github.com/tiago-aguiar/at-way
